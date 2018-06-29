This repo is a sample to reproduce the issue described here - https://stackoverflow.com/questions/51085138/kotlin-data-class-and-bean-validation-with-container-element-constraints

The test in [BeanValidationContainerConstraintsApplicationTests](src/test/kotlin/com/example/beanvalidationcontainerconstraints/BeanValidationContainerConstraintsApplicationTests.kt) fails because it expects two validation errors:

- `name` is empty
- the map value in `someMap`
 
```kotlin
data class SamplePayload(
    @field:NotEmpty val name: String,
    @field:NotEmpty val someMap: Map<String, @NotEmpty String>)
```

The `@NotEmpty` annotation on the **value type** of the map `someMap` is not considered.

See https://beanvalidation.org/2.0/spec/#constraintdeclarationvalidationprocess-containerelementconstraints.

Run the test with:

```
./gradlew test
```
