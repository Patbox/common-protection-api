# Common Protection API

This is simple api for mods to check if action is allowed in a region. 
Its main idea is to have one single common api for checking against multiple mods.

For claim/protection mods, it adds simple handler system you can implement.

# Getting Started

To begin, you need to add Nucleoid's maven to your build `build.gradle`.

```groovy
repositories {
    // There might be other repos there too, just add it at the end
    maven { url "https://maven.nucleoid.xyz/" }
}
```

Then you just declare it as dependency!
```groovy
dependencies {
    // You will have other dependencies here too
    
	modImplementation include("eu.pb4:common-protection-api:[VERSION]")
}
```
This will also include it in yours mods, so users won't need to download it separately.

You just need to replace `[VERSION]` with version you want to use (which should be usually the latest available).
For list of version names, you can check [maven](https://maven.nucleoid.xyz/eu/pb4/common-protection-api/)

To check, you just need to call static methods in `eu.pb4.common.protection.api.CommonProtection` class.

Claim mods need to create a object extending `eu.pb4.common.protection.api.ProtectionProvider` 
and register it with `CommonProtection.register(Identifier, ProtectionProvider)`



