package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.ComplexQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.ComplexVector
import io.github.ltennstedt.finnmath.number.complex.Complex
import java.math.BigDecimal
import java.math.MathContext

public class ComplexMatrix(
    entries: List<MatrixEntry<Complex>>
) : AbstractMatrix<Complex, Complex, ComplexVector, ComplexMatrix, Double, Double>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<Complex, Complex, ComplexVector, ComplexMatrix>
        get() = ComplexQuotientField

    override fun maxAbsColumnSumNorm(): Double {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): Double {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): Double {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): Double {
        TODO("Not yet implemented")
    }
}
