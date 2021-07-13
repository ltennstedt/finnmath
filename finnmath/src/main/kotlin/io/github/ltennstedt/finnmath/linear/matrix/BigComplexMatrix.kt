package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.BigComplexQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.BigComplexVector
import io.github.ltennstedt.finnmath.number.complex.BigComplex
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

public class BigComplexMatrix(
    entries: List<MatrixEntry<BigComplex>>
) : AbstractMatrix<BigComplex, BigComplex, BigComplexVector, BigComplexMatrix, BigInteger, BigDecimal>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<BigComplex, BigComplex, BigComplexVector, BigComplexMatrix>
        get() = BigComplexQuotientField

    override fun maxAbsColumnSumNorm(): BigInteger {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): BigInteger {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): BigInteger {
        TODO("Not yet implemented")
    }
}
