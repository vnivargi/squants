/*                                                                      *\
** Squants                                                              **
**                                                                      **
** Scala Quantities and Units of Measure Library and DSL                **
** (c) 2013-2014, Gary Keorkunian                                       **
**                                                                      **
\*                                                                      */

package squants.mass

import squants._

/**
 * @author  garyKeorkunian
 * @since   0.1
 *
 * @param value in [[squants.mass.Moles]]
 */
final class ChemicalAmount private (val value: Double) extends Quantity[ChemicalAmount]
    with BaseQuantity with PhysicalQuantity {

  def valueUnit = ChemicalAmount.valueUnit
  def baseUnit = Moles

  def /(that: Volume) = ??? // returns SubstanceConcentration

  def toMoles = to(Moles)
  def toPoundMoles = to(PoundMoles)
}

object ChemicalAmount {
  // TODO Consider implementing this pattern in all Q's
  def valueUnit = Moles
  private[mass] def apply[A](n: A)(implicit num: Numeric[A]) = new ChemicalAmount(num.toDouble(n))
}

trait ChemicalAmountUnit extends BaseQuantityUnit[ChemicalAmount] with UnitMultiplier {
  def apply[A](n: A)(implicit num: Numeric[A]) = ChemicalAmount(convertFrom(n))
  def dimensionSymbol = "N"
}

object Moles extends ChemicalAmountUnit with ValueUnit with BaseUnit {
  val symbol = "mol"
}

object PoundMoles extends ChemicalAmountUnit {
  val symbol = "lb-mol"
  val multiplier = 453.59237
}

object ChemicalAmountConversions {
  lazy val mole = Moles(1)
  lazy val poundMole = PoundMoles(1)

  implicit class ChemicalAmountConversions[A](n: A)(implicit num: Numeric[A]) {
    def moles = Moles(n)
    def poundMoles = PoundMoles(n)
  }

  implicit object ChemicalAmountNumeric extends AbstractQuantityNumeric[ChemicalAmount](ChemicalAmount.valueUnit)
}