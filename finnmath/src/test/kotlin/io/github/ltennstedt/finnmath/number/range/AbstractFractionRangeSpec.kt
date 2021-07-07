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

package io.github.ltennstedt.finnmath.number.range

import io.github.ltennstedt.finnmath.number.fraction.Fraction
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AbstractFractionRangeSpec : FunSpec({
    test("length should return length") {
        val a = Fraction(1L, 2L)
        val b = Fraction(3L, 2L)
        FractionRange(a, b).length shouldBe Fraction(1L, 1L)
    }
})
