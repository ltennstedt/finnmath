/*
 * Copyright 2021 Lars Tennstedt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ltennstedt.finnmath.linear.field

import io.github.ltennstedt.finnmath.number.complex.BigComplex

/**
 * Single implementation of the [BigComplex] field
 *
 * @author Lars Tennstedt
 * @since 0.0.1
 */
public object BigComplexField : Field<BigComplex, BigComplex> {
    override val addition: (a: BigComplex, b: BigComplex) -> BigComplex
        get() = BigComplex::add

    override val subtraction: (a: BigComplex, b: BigComplex) -> BigComplex
        get() = BigComplex::subtract

    override val multiplication: (a: BigComplex, b: BigComplex) -> BigComplex
        get() = BigComplex::multiply

    override val division: (a: BigComplex, b: BigComplex) -> BigComplex
        get() = BigComplex::divide

    override val equalityByComparing: (a: BigComplex, b: BigComplex) -> Boolean
        get() = BigComplex::equalsByComparing

    override val zero: BigComplex
        get() = BigComplex.ZERO
}
