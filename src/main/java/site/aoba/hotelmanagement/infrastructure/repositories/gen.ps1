$domainClassFiles = Get-ChildItem "../models/*.java"
foreach($domainClassFile in $domainClassFiles) {
    $domainClass = Get-Content -Raw $domainClassFile;
    $match = [regex]::Match($domainClass, "public class (?<className>.+?) implements IEntity\<(?<typeName>.+?)\>");
    $content = @"
package site.aoba.hotelmanagement.domain.repository;

import site.aoba.hotelmanagement.architecture.domain.repository.IRepository;
import site.aoba.hotelmanagement.domain.models.$($match.Groups["className"].Value);

public interface I$($match.Groups["className"].Value)Repository extends IRepository<$($match.Groups["typeName"].Value), $($match.Groups["className"].Value)> {
}
"@;
    $content | Out-File "I$($match.Groups["className"].Value)Repository.java";
}