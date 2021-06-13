# finnmath: Mathematical library for the JVM and Android written in Kotlin

![](https://github.com/ltennstedt/finnmath/workflows/Java%20CI/badge.svg)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=bugs)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=code_smells)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=coverage)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=ncloc)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=alert_status)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=security_rating)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=sqale_index)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_finnmath&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

finnmath is a free software library for the [JVM](https://www.java.com/) and [Android](https://www.android.com/) 
written in [Kotlin](https://kotlinlang.org/) which provides or will provide implementations of fractions, complex 
numbers, matrices and vectors and their arithmetic. This library is designed with Kotlin and Java users in mind but 
the Kotlin support is the main driver for this project (Sequences over Streams, no special treatment of primitives 
etc.). The great [Apache Commons Math](https://commons.apache.org/proper/commons-math/) and the new 
[Apache Commons Numbers](https://commons.apache.org/proper/commons-numbers/) provide less cluttered Java APIs and 
support implementations based on primitives as well.

What finnmath will provide:

* Vectors and matrices
* Fractions and complex numbers
* Determinant calculation
* Custom Kotest matchers (separate module)
* Custom AssertJ assertions (separate module)

What finnmath will not provide:

* Solver for equations
* Matrix decomposition, reduction or factorization
* Polynomials

Previously finnmath was written in [Java](https://www.java.com/) and used [Maven](https://maven.apache.org/) as its 
build automation tool but currently the port to [Kotlin](https://kotlinlang.org/) and [Gradle](https://gradle.org/) 
is in progress. It should interoperate seamlessly with other JVM technologies. Dependencies are 
[big-math](https://eobermuhlner.github.io/big-math/),
[KotlinDiscreteMathToolkit](https://github.com/MarcinMoskala/KotlinDiscreteMathToolkit) and 
[Guava](https://guava.dev/) and for tests 
[Kotest](https://kotest.io/), [MockK](https://mockk.io/),
[JUnit](https://junit.org/junit5/) and [AssertJ](https://assertj.github.io/doc/)

## Building

A JDK in version 8 and [Gradle](https://gradle.org/) 7 is needed to build this project.

```shell script
gradle default
```

## Implementation details

* Kotlin 1.5 and Java 8
* The matrices are based on `com.google.common.collect.ImmutableTable` from Guava.
* The vectors are based on `kotlin.collections.Map` from Kotlin which is read-only.
* All types are immutable.
* Absolutely no null values
* Numbers extend `kotlin.Number` and `finnmath.number.BigFraction` implements `kotlin.Comparable`
* Comparators implements `kotlin.Comparator`
* Pseudo random generators are based on `kotlin.random.Random`
* Lambdas, Sequences and Streams
* Classes of numbers, vectors and matrices are final.
* Classes of random generators are open for better compatibility with DI containers
* Useful hashCode, equals and toString methods
* Data classes
* Builders for vectors and matrices (Java style and Kotlin's type-safe builders)
* Parameter validation and fast failing
* Singletons via objects
* Extension functions for `kotlin.Int`, `kotlin.Long`, `kotlin.Float`, `kotlin.Double`, `java.math.BigInteger` and
  `java.math.BigDecimal`
* Operators for numbers, vectors and matrices

## Usage

### Kotlin

```kotlin
// Operators
BigFraction(2, 3) + BigFraction(3, 4)

// Extensions
12.toBigGaussian()

// Getting an element of a vector or matrix
vector[1]
matrix[2, 3]

// Check if an element is contained in a vector or matrix
when (BigInteger.ZERO) {
    in vector -> true
    in matrix -> true
    else -> false
}

// Type safe builders for vectors and matrices
bigIntegerVector {
    size = 3
    computationForAbsent = { _ -> BigIntegerRandom().nextNumber() }
    entry {
        index = 2
        element = BigInteger.ONE
    }
    entry {
        index = 3
        element = BigInteger.valueOf(2)
    }
}
```

### Java

```java
// Classic builders for vectors and matrices
// Fluent API
class A {
    void m() {
        BigIntegerMatrix.builder(2, 2)
                .put(1, 2, BigInteger.ONE)
                .put(2, 1, BigInteger.valueOf(2))
                .put(2, 2, BigInteger.valueOf(3))
                .nullsToZeros()
                .build();

        // Lambdas
        BigIntegerMatrix.builder(2, 2)
                .computeIfAbsent(BigIntegerRandom::nextNumber)
                .build();
    }
}
```

## Goals

* Complete KDoc/Javadoc (generated partially by [Dokka](https://github.com/Kotlin/dokka))
* Clean code
* High code quality with the help of [ktlint](https://ktlint.github.io/), [detekt](https://detekt.github.io/detekt/),
  [SonarLint](https://www.sonarlint.org/), [SonarQube](https://www.sonarqube.org/) and 
  [SonarCloud](https://sonarcloud.io/)
* High code coverage with the help of [Kotest](https://kotest.io/), [MockK](https://mockk.io/), 
  [JUnit](https://junit.org/junit5/), [AssertJ](https://assertj.github.io/doc/), 
  [JaCoCo](https://www.jacoco.org/jacoco/), [SonarQube](https://www.sonarqube.org/) and
  [SonarCloud](https://sonarcloud.io/)
* Idiomatic Kotlin and Java API
* Stay up to date with versions of libraries and plugins
* Object-oriented design
* Use Kotlin features to create a DSL-like API

Please keep in mind that perfection is not reachable, impressions are very subjective and definitions can vary! ;-)

## Links

### finnmath

* [KDoc](https://ltennstedt.github.io/finnmath/finnmath/dokka/html/index.html)
* [Javadoc](https://ltennstedt.github.io/finnmath/finnmath/dokka/javadoc/index.html)
* [Gradle Site](https://ltennstedt.github.io/finnmath/finnmath/docs/site/index.html)
* [finnmath on SonarCloud](https://sonarcloud.io/dashboard?id=ltennstedt_finnmath)
* [detekt report](https://ltennstedt.github.io/finnmath/finnmath/reports/detekt/detekt.html)
* [Build Dashboard](https://ltennstedt.github.io/finnmath/finnmath/reports/buildDashboard/index.html)
* [Dependency Updates report](https://ltennstedt.github.io/finnmath/finnmath/dependencyUpdates/report.html)

### finnmath-kotest

* [KDoc](https://ltennstedt.github.io/finnmath/finnmath-kotest/dokka/html/index.html)
* [Javadoc](https://ltennstedt.github.io/finnmath/finnmath-kotest/dokka/javadoc/index.html)
* [Gradle Site](https://ltennstedt.github.io/finnmath/finnmath-kotest/docs/site/index.html)
* [detekt report](https://ltennstedt.github.io/finnmath/finnmath-kotest/reports/detekt/detekt.html)
* [Build Dashboard](https://ltennstedt.github.io/finnmath/finnmath-kotest/reports/buildDashboard/index.html)
* [Dependency Updates report](https://ltennstedt.github.io/finnmath/finnmath-kotest/dependencyUpdates/report.html)

### finnmath-assertj

* [Javadoc](https://ltennstedt.github.io/finnmath/finnmath-assertj/docs/javadoc/index.html)
* [Gradle Site](https://ltennstedt.github.io/finnmath/finnmath-assertj/docs/site/index.html)
* [Build Dashboard](https://ltennstedt.github.io/finnmath/finnmath-assertj/reports/buildDashboard/index.html)
* [Dependency Updates report](https://ltennstedt.github.io/finnmath/finnmath-assertj/dependencyUpdates/report.html)

## Information

finnmath is open source and free software and is licensed under the permissive Apache License.

finnmath is still in a very early state and a work in progress.

## Developing

The code formatting follows the ktlint code style.

## What about multiplatform and opt-in requirements support ?

Maybe when these features are stable, [this bug](https://youtrack.jetbrains.com/issue/KT-17345) is fixed, all needed 
functionality of the JVM-only dependencies have a multiplatform alternative (table implementation and 
permutations) and I will have the time.

### Who is Finn?

* [Long video](https://www.youtube.com/watch?v=Z8-rtor3G9Q)
* [Short video](https://www.youtube.com/watch?v=0DZ1VT5kbw4)