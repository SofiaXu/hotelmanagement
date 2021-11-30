function Get-UpperFirst {
    param (
        [string]$fieldName
    )
    return $fieldName.Substring(0, 1).ToUpper() + $fieldName.Substring(1);
}

$domainClassFiles = Get-ChildItem "../../domain/models/*.java"
foreach ($domainClassFile in $domainClassFiles) {
    $domainClass = Get-Content -Raw $domainClassFile;
    $domainMatch = [regex]::Match($domainClass, "public class (?<className>.+?) implements IEntity\<(?<typeName>.+?)\>");
    $domainFieldMatches = [regex]::Matches($domainClass, "    private (?<typeName>.+?) (?<fieldName>.+?);");
    $content = @"
package site.aoba.hotelmanagement.infrastructure.repositories;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import lombok.RequiredArgsConstructor;
import lombok.val;
import site.aoba.hotelmanagement.domain.models.$($domainMatch.Groups['className']);
import site.aoba.hotelmanagement.domain.repository.I$($domainMatch.Groups['className'])Repository;
import site.aoba.hotelmanagement.infrastructure.mappers.$($domainMatch.Groups['className'])Mapper;
import site.aoba.hotelmanagement.infrastructure.models.$($domainMatch.Groups['className'])Model;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class $($domainMatch.Groups['className'])Repository implements I$($domainMatch.Groups['className'])Repository {
    private final $($domainMatch.Groups['className'])Mapper mapper;

    @Override
    public List<$($domainMatch.Groups['className'])> getEntities(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return mapper
                .selectAll().stream().map(x -> toEntity(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<$($domainMatch.Groups['className'])> getEntities(int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return getEntities(pageSize, pageIndex);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public $($domainMatch.Groups['className']) getEntityById($($domainMatch.Groups['typeName']) id) {
        val x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return toEntity(x);
    }

    @Override
    public $($domainMatch.Groups['className']) getEntityById($($domainMatch.Groups['typeName']) id, boolean refresh) {
        if (refresh)
            clearItemCache(id);

        return getEntityById(id);
    }

    @Override
    public void updateEntity($($domainMatch.Groups['className']) entity) {
        mapper.updateByPrimaryKey(toModel(entity));
    }

    @Override
    public void createEntity($($domainMatch.Groups['className']) entity) {
        mapper.insert(toModel(entity));
    }

    @Override
    public void deleteEntity($($domainMatch.Groups['typeName']) id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<$($domainMatch.Groups['className'])> searchEntities(Predicate<$($domainMatch.Groups['className'])> entityPredicate, int pageSize, int pageIndex) {
        return mapper.selectAll().stream()
                .map(x -> toEntity(x))
                .filter(entityPredicate).collect(Collectors.toList());
    }

    @Override
    public List<$($domainMatch.Groups['className'])> searchEntities(Predicate<$($domainMatch.Groups['className'])> entityPredicate, int pageSize, int pageIndex, boolean refresh) {
        if (refresh)
            clearItemsCache();
        return searchEntities(entityPredicate, pageSize, pageIndex);
    }

    @Override
    @CacheEvict(key = "#id")
    public void clearItemCache($($domainMatch.Groups['typeName']) id) {
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearItemsCache() {
    }

    private $($domainMatch.Groups['className'])Model toModel($($domainMatch.Groups['className']) entity) {
        val x = new $($domainMatch.Groups['className'])Model();
        $(foreach ($match in $domainFieldMatches) {
"        x.set" + (Get-UpperFirst $match.Groups["fieldName"].Value) + "(entity.get" + (Get-UpperFirst $match.Groups["fieldName"].Value) + "());`n"
        })
        return x;
    }

    private $($domainMatch.Groups['className']) toEntity($($domainMatch.Groups['className'])Model x) {
        return new $($domainMatch.Groups['className'])(
            $(for ($i = 0; $i -lt $domainFieldMatches.Groups.length; $i++) {
                "x.get" + (Get-UpperFirst $domainFieldMatches[$i].Groups["fieldName"].Value) + "(), `n"
                        })
        );
    }
}

"@;
    $content | Out-File "$($domainMatch.Groups["className"].Value)Repository.java";
}