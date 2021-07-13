package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.LongQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.LongVector
import java.math.BigDecimal
import java.math.MathContext

public class LongMatrix(
    entries: List<MatrixEntry<Long>>
) : AbstractMatrix<Long, Double, LongVector, LongMatrix, Double, Long>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<Long, Double, LongVector, LongMatrix>
        get() = LongQuotientField

    override fun maxAbsColumnSumNorm(): Double {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): Double {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): Long {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): Double {
        TODO("Not yet implemented")
    }
}
