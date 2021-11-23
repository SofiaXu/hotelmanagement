$javaFiles = Get-ChildItem "../models/*.java"
foreach($javaFile in $javaFiles) {
    $javaCode = Get-Content -Raw $javaFile;
    $match = [regex]::Match($javaCode, "public class (?<className>.+?) implements IEntity\<(?<typeName>.+?)\>");
    $content = @"
package site.aoba.hotelmanagement.domain.repository;

import site.aoba.hotelmanagement.architecture.domain.repository.IRepository;
import site.aoba.hotelmanagement.domain.models.$($match.Groups["className"].Value);

public interface $($match.Groups["className"].Value)Repository extends IRepository<$($match.Groups["typeName"].Value), $($match.Groups["className"].Value)> {
}
"@;
    $content | Out-File "$($match.Groups["className"].Value)Repository.java";
}