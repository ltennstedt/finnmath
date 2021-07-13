/*
 * Copyright 2017 Lars Tennstedt
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

package io.github.ltennstedt.finnmath.linear.matrix

import com.google.common.base.MoreObjects
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.AbstractVector
import io.github.ltennstedt.finnmath.linear.vector.VectorEntry
import java.math.BigDecimal
import java.math.MathContext
import java.util.Objects

/**
 * Base class for matrices
 *
 * @param E type of elements
 * @param Q type of the quotient
 * @param V type of the vector
 * @param M type of the matrix
 * @param N type of the maximum absolute column sum norm, maximum absolute row sum norm and the maximum norm
 * @param B type of the square of the norms
 * @property entries entries
 * @constructor Constructs an [AbstractMatrix]
 * @throws IllegalArgumentException if [entries] is empty
 * @throws IllegalArgumentException if [rowIndices] `!= expectedRowIndices`
 * @throws IllegalArgumentException if [columnIndices] `!= expectedColumnIndices`
 * @throws IllegalArgumentException if one value of [entries] is null
 * @author Lars Tennstedt
 * @since 0.0.1
 */
public abstract class AbstractMatrix<
    E : Number,
    Q : Number,
    V : AbstractVector<E, Q, V, *, *>,
    M : AbstractMatrix<E, Q, V, M, N, B>,
    N,
    B
    >(
    entries: List<MatrixEntry<E>>
) {
    /**
     * Entries
     *
     * @since 0.0.1
     */
    public val entries: List<MatrixEntry<E>> = entries.sorted()

    /**
     * Indicates if this is square
     *
     * @since 0.0.1
     */
    public val isSquare: Boolean
        get() = rowSize == columnSize

    /**
     * Indicates if this is triangular
     *
     * @since 0.0.1
     */
    public val isTriangular: Boolean
        get() = isUpperTriangular || isLowerTriangular

    /**
     * Indicates if this is upper triangular
     *
     * @since 0.0.1
     */
    public val isUpperTriangular: Boolean
        get() = entries.filter { (r, c, _) -> r > c }
            .map { it.element }
            .all { this.quotientField.equalityByComparing(it, quotientField.zero) }

    /**
     * Indicates if this is lower triangular
     *
     * @since 0.0.1
     */
    public val isLowerTriangular: Boolean
        get() = entries.filter { (r, c, _) -> r < c }
            .map { it.element }
            .all { this.quotientField.equalityByComparing(it, quotientField.zero) }

    /**
     * Indicates if this is diagonal
     *
     * @since 0.0.1
     */
    public val isDiagonal: Boolean
        get() = isUpperTriangular && isLowerTriangular

    /**
     * Indicates if this is the identity one
     *
     * @since 0.0.1
     */
    public val isIdentity: Boolean
        get() = isDiagonal && diagonalElements.all { quotientField.equalityByComparing(it, quotientField.zero) }

    /**
     * Indicates if this is invertible
     *
     * @since 0.0.1
     */
    public abstract val isInvertible: Boolean

    /**
     * Indicates if this is symmetric
     *
     * @since 0.0.1
     */
    public val isSymmetric: Boolean by lazy { isSquare && equalsByComparing(transpose()) }

    /**
     * Indicates if this is skew symmetric
     *
     * @since 0.0.1
     */
    public val isSkewSymmetric: Boolean by lazy { isSquare && transpose().equalsByComparing(negate()) }

    /**
     * Row indices
     *
     * @since 0.0.1
     */
    public val rowIndices: List<Int> by lazy { this.entries.map { it.rowIndex } }

    /**
     * Column indices
     *
     * @since 0.0.1
     */
    public val columnIndices: List<Int> by lazy { this.entries.map { it.columnIndex } }

    /**
     * Elements
     *
     * @since 0.0.1
     */
    public val elements: List<E> by lazy { this.entries.map { it.element } }

    /**
     * Diagonal elements
     *
     * @since 0.0.1
     */
    public val diagonalElements: List<E> by lazy { this.entries.filter { (r, c, _) -> r == c }.map { it.element } }

    /**
     * Diagonal [MatrixEntries][MatrixEntry]
     *
     * @since 0.0.1
     */
    public val diagonalEntries: List<MatrixEntry<E>> by lazy { this.entries.filter { (r, c, _) -> r == c } }

    /**
     * Row size
     *
     * @since 0.0.1
     */
    public val rowSize: Int
        get() = rowIndices.size

    /**
     * Column size
     *
     * @since 0.0.1
     */
    public val columnSize: Int
        get() = columnIndices.size

    /**
     * Field
     *
     * @since 0.0.1
     */
    protected abstract val quotientField: QuotientField<E, Q, V, M>

    init {
        require(entries.isNotEmpty()) { "expected entries not be empty but entries = $entries" }
        val expectedRowIndices = (1..rowSize).toList()
        require(rowIndices == expectedRowIndices) {
            "expected rowIndices == expectedRowIndices but $rowIndices != $expectedRowIndices"
        }
        val expectedColumnIndices = (1..columnSize).toList()
        require(columnIndices == expectedColumnIndices) {
            "expected columnIndices == expectedColumnIndices but $columnIndices != $expectedColumnIndices"
        }
    }

    /**
     * Returns the sum of this and the [summand]
     *
     * @throws IllegalArgumentException if [rowSize] != `summand.rowSize`
     * @throws IllegalArgumentException if [columnSize] != `summand.columnSize`
     * @since 0.0.1
     */
    public fun add(summand: M): M {
        require(rowSize == summand.rowSize) { "Equal row sizes expected but $rowSize != ${summand.rowSize}" }
        require(columnSize == summand.columnSize) {
            "Equal column sizes expected but $columnSize != ${summand.columnSize}"
        }
        return quotientField.matrixConstructor(
            entries.map { (r, c, e) -> MatrixEntry(r, c, quotientField.addition(e, summand[r, c])) }
        )
    }

    /**
     * Returns the difference of this and the [subtrahend]
     *
     * @throws IllegalArgumentException if [rowSize] != `subtrahend.rowSize`
     * @throws IllegalArgumentException if [columnSize] != `subtrahend.columnSize`
     * @since 0.0.1
     */
    public fun subtract(subtrahend: M): M {
        require(rowSize == subtrahend.rowSize) { "Equal row sizes expected but $rowSize != ${subtrahend.rowSize}" }
        require(columnSize == subtrahend.columnSize) {
            "Equal column sizes expected but $columnSize != ${subtrahend.columnSize}"
        }
        return quotientField.matrixConstructor(
            entries.map { (r, c, e) -> MatrixEntry(r, c, quotientField.subtraction(e, subtrahend[r, c])) }
        )
    }

    /**
     * Returns the product of this and the [factor]
     *
     * @throws IllegalArgumentException if [columnSize] != `factor.rowSize`
     * @since 0.0.1
     */
    public fun multiply(factor: M): M {
        require(columnSize == factor.rowSize) {
            "Equal column sizes expected but $columnSize != ${factor.rowSize}"
        }
        TODO()
    }

    /**
     * Returns the product of this and the [vector]
     *
     * @throws IllegalArgumentException if [columnSize] != `vector.size`
     * @since 0.0.1
     */
    public fun multiplyVector(vector: V): V {
        require(columnSize == vector.size) {
            "columnSize == vector.size expected but $columnSize != ${vector.size}"
        }
        TODO()
    }

    /**
     * Returns the scalar product of this and the [scalar]
     *
     * @since 0.0.1
     */
    public fun scalarMultiply(scalar: E): M = quotientField.matrixConstructor(
        entries.map { (r, c, e) -> MatrixEntry(r, c, quotientField.multiplication(scalar, e)) }
    )

    /**
     * Returns the negated [AbstractMatrix] and this
     *
     * @since 0.0.1
     */
    public fun negate(): M = scalarMultiply(quotientField.negation(quotientField.one))

    /**
     * Returns the trace
     *
     * @since 0.0.1
     */
    public fun trace(): E = addDiagonalElements()

    /**
     * Returns the determinant
     *
     * @since 0.0.1
     */
    public fun determinant(): E {
        check(isSquare) { "expected square matrix but $rowSize != $columnSize" }
        return when {
            isTriangular -> addDiagonalElements()
            rowSize > 3 -> leibnizFormula()
            rowSize == 3 -> ruleOfSarrus()
            else -> determinantOf2x2Matrix()
        }
    }

    /**
     * Returns the transpose
     *
     * @since 0.0.1
     */
    public fun transpose(): M = quotientField.matrixConstructor(entries.map { (i, c, e) -> MatrixEntry(c, i, e) })

    /**
     * Returns the minor dependent on the [rowIndex] and [columnIndex]
     *
     * @throws IllegalArgumentException if [rowIndex] !in 1..[rowSize]
     * @throws IllegalArgumentException if [columnIndex] !in 1..[columnSize]
     * @since 0.0.1
     */
    public fun minor(rowIndex: Int, columnIndex: Int): M {
        require(rowIndex in 1..rowSize) { "expected rowIndex in 1..$rowSize but rowIndex = $rowIndex" }
        require(columnIndex in 1..columnSize) {
            "expected columnIndex in 1..$columnSize but columnIndex = $columnIndex"
        }
        TODO()
    }

    /**
     * Returns the maximum absolute column sum norm
     *
     * @since 0.0.1
     */
    public abstract fun maxAbsColumnSumNorm(): N

    /**
     * Returns the maximum absolute row sum norm
     *
     * @since 0.0.1
     */
    public abstract fun maxAbsRowSumNorm(): N

    /**
     * Returns the square of the frobenius norm
     *
     * @since 0.0.1
     */
    public abstract fun frobeniusNormPow2(): B

    /**
     * Returns the frobenius norm based on the [mathContext]
     *
     * @since 0.0.1
     */
    public abstract fun frobeniusNorm(mathContext: MathContext): BigDecimal

    /**
     * Returns the maximum norm
     *
     * @since 0.0.1
     */
    public abstract fun maxNorm(): N

    /**
     * Returns the element at the [rowIndex] and [columnIndex]
     *
     * @throws IllegalArgumentException if [rowIndex] !in 1..[rowSize]
     * @throws IllegalArgumentException if [columnIndex] !in 1..[columnSize]
     * @since 0.0.1
     */
    public operator fun get(rowIndex: Int, columnIndex: Int): E {
        require(rowIndex in 1..rowSize) { "expected rowIndex in 1..$rowSize but rowIndex = $rowIndex" }
        require(columnIndex in 1..columnSize) {
            "expected columnIndex in 1..$columnSize but columnIndex = $columnIndex"
        }
        return entry(rowIndex, columnIndex).element
    }

    /**
     * Returns the [MatrixEntry] at the [rowIndex] and [columnIndex]
     *
     * @throws IllegalArgumentException if [rowIndex] !in 1..[rowSize]
     * @throws IllegalArgumentException if [columnIndex] !in 1..[columnSize]
     * @since 0.0.1
     */
    public fun entry(rowIndex: Int, columnIndex: Int): MatrixEntry<E> {
        require(rowIndex in 1..rowSize) { "expected rowIndex in 1..$rowSize but rowIndex = $rowIndex" }
        require(columnIndex in 1..columnSize) {
            "expected columnIndex in 1..$columnSize but columnIndex = $columnIndex"
        }
        @Suppress("UNCHECKED_CAST")
        return entries.single { (r, c, _) -> r == rowIndex && c == columnIndex }
    }

    /**
     * Returns the row at the [rowIndex]
     *
     * @throws IllegalArgumentException if [rowIndex] !in 1..[rowSize]
     * @since 0.0.1
     */
    public fun row(rowIndex: Int): Map<Int, E> {
        require(rowIndex in 1..rowSize) { "expected rowIndex in 1..$rowSize but rowIndex = $rowIndex" }
        return entries.sorted().filter { it.rowIndex == rowIndex }.associate { (_, c, e) -> c to e }
    }

    /**
     * Returns the column at the [columnIndex]
     *
     * @throws IllegalArgumentException if [columnIndex] !in 1..[columnSize]
     * @since 0.0.1
     */
    public fun column(columnIndex: Int): Map<Int, E> {
        require(columnIndex in 1..columnSize) {
            "expected columnIndex in 1..$columnSize but columnIndex = $columnIndex"
        }
        return entries.sorted().filter { it.columnIndex == columnIndex }.associate { (r, _, e) -> r to e }
    }

    /**
     * Returns the row vector at the [rowIndex]
     *
     * @throws IllegalArgumentException if [rowIndex] !in 1..[rowSize]
     * @since 0.0.1
     */
    public fun rowVector(rowIndex: Int): V {
        require(rowIndex in 1..rowSize) { "expected rowIndex in 1..$rowSize but rowIndex = $rowIndex" }
        return quotientField.vectorConstructor(
            entries.filter { it.rowIndex == rowIndex }.map { (_, c, e) -> VectorEntry(c, e) }
        )
    }

    /**
     * Returns the column vector at the [columnIndex]
     *
     * @throws IllegalArgumentException if [columnIndex] !in 1..[columnSize]
     * @since 0.0.1
     */
    public fun columnVector(columnIndex: Int): V {
        require(columnIndex in 1..columnSize) {
            "expected columnIndex in 1..$columnSize but columnIndex = $columnIndex"
        }
        return quotientField.vectorConstructor(
            entries.filter { it.columnIndex == columnIndex }.map { (r, _, e) -> VectorEntry(r, e) }
        )
    }

    /**
     * Returns if [element] is contained in [V]
     *
     * @since 0.0.1
     */
    public operator fun contains(element: E): Boolean = element in elements

    /**
     * Returns if this is equal to [other] by comparing elements
     *
     * @throws IllegalArgumentException if [rowSize] != `other.rowSize`
     * @throws IllegalArgumentException if [columnSize] != `other.columnSize`
     * @since 0.0.1
     */
    public fun equalsByComparing(other: M): Boolean =
        entries.all { (r, c, e) -> quotientField.equalityByComparing(e, other[r, c]) }

    /**
     * Returns if this is not equal to [other] by comparing elements
     *
     * @throws IllegalArgumentException if [rowSize] != `other.rowSize`
     * @throws IllegalArgumentException if [columnSize] != `other.columnSize`
     * @since 0.0.1
     */
    public fun doesNotEqualByComparing(other: M): Boolean = !equalsByComparing(other)

    /**
     * Add all [diagonalElements] and returns the sum
     *
     * @since 0.0.1
     */
    protected fun addDiagonalElements(): E = elements.reduce { a, b -> quotientField.addition(a, b) }

    /**
     * Leibniz formula
     *
     * @return determinant
     * @since 0.0.1
     */
    protected fun leibnizFormula(): E = TODO()

    /**
     * Rule of Sarrus
     *
     * @return determinant
     * @since 0.0.1
     */
    protected fun ruleOfSarrus(): E = TODO()

    /**
     * Calculate determinant of a 2x2 matrix
     *
     * @return determinant
     * @since 0.0.1
     */
    protected fun determinantOf2x2Matrix(): E = TODO()

    override fun hashCode(): Int = Objects.hash(entries)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractMatrix<*, *, *, *, *, *>) return false
        return entries == other.entries
    }

    override fun toString(): String = MoreObjects.toStringHelper(this).add("entries", entries).toString()

    public companion object
}
