/*                                                                      *\
** Squants                                                              **
**                                                                      **
** Scala Quantities and Units of Measure Library and DSL                **
** (c) 2013-2014, Gary Keorkunian                                       **
**                                                                      **
\*                                                                      */

package squants.electro

import squants._

/**
 * @author  garyKeorkunian
 * @since   0.1
 *
 * @param value value in [[squants.electro.Ohms]]
 */
final class ElectricalResistance private (val value: Double)
    extends Quantity[ElectricalResistance] {

  def valueUnit = Ohms

  def *(that: ElectricCurrent): ElectricPotential = Volts(toOhms * that.toAmperes)
  def *(that: Length): Resistivity = OhmMeters(toOhms * that.toMeters)

  def toOhms = to(Ohms)
  def toNanohms = to(Nanohms)
  def toMicrohms = to(Microohms)
  def toMillohms = to(Milliohms)
  def toKilohms = to(Kilohms)
  def toMegohms = to(Megohms)
  def toGigohms = to(Gigohms)

  def inSiemens = Siemens(1.0 / to(Ohms))
}

object ElectricalResistance {
  private[electro] def apply[A](n: A)(implicit num: Numeric[A]) = new ElectricalResistance(num.toDouble(n))
}

trait ElectricalResistanceUnit extends UnitOfMeasure[ElectricalResistance] with UnitMultiplier {
  def apply[A](n: A)(implicit num: Numeric[A]) = ElectricalResistance(convertFrom(n))
}

object Ohms extends ElectricalResistanceUnit with ValueUnit {
  val symbol = "Ω"
}

object Nanohms extends ElectricalResistanceUnit {
  val symbol = "nΩ"
  val multiplier = MetricSystem.Nano
}

object Microohms extends ElectricalResistanceUnit {
  val symbol = "µΩ"
  val multiplier = MetricSystem.Micro
}

object Milliohms extends ElectricalResistanceUnit {
  val symbol = "mΩ"
  val multiplier = MetricSystem.Milli
}

object Kilohms extends ElectricalResistanceUnit {
  val symbol = "kΩ"
  val multiplier = MetricSystem.Kilo
}

object Megohms extends ElectricalResistanceUnit {
  val symbol = "MΩ"
  val multiplier = MetricSystem.Mega
}

object Gigohms extends ElectricalResistanceUnit {
  val symbol = "GΩ"
  val multiplier = MetricSystem.Giga
}

object ElectricalResistanceConversions {
  lazy val ohm = Ohms(1)
  lazy val nanohm = Nanohms(1)
  lazy val microohm = Microohms(1)
  lazy val milliohm = Milliohms(1)
  lazy val kilohm = Kilohms(1)
  lazy val megohm = Megohms(1)
  lazy val gigohm = Gigohms(1)

  implicit class ElectricalResistanceConversions[A](n: A)(implicit num: Numeric[A]) {
    def ohms = Ohms(n)
    def nanohms = Nanohms(n)
    def microohms = Microohms(n)
    def milliohms = Milliohms(n)
    def kilohms = Kilohms(n)
    def megohms = Megohms(n)
    def gigohms = Gigohms(n)
  }

  implicit object ElectricalResistanceNumeric
    extends AbstractQuantityNumeric[ElectricalResistance](Ohms)
}
