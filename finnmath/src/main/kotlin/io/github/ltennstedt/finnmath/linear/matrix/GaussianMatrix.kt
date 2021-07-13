package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.GaussianQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.GaussianVector
import io.github.ltennstedt.finnmath.number.complex.Complex
import io.github.ltennstedt.finnmath.number.complex.Gaussian
import java.math.BigDecimal
import java.math.MathContext

public class GaussianMatrix(
    entries: List<MatrixEntry<Gaussian>>
) : AbstractMatrix<Gaussian, Complex, GaussianVector, GaussianMatrix, Long, Double>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<Gaussian, Complex, GaussianVector, GaussianMatrix>
        get() = GaussianQuotientField

    override fun maxAbsColumnSumNorm(): Long {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): Long {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): Double {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): Long {
        TODO("Not yet implemented")
    }
}
