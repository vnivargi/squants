/*                                                                      *\
** Squants                                                              **
**                                                                      **
** Scala Quantities and Units of Measure Library and DSL                **
** (c) 2013-2014, Gary Keorkunian                                       **
**                                                                      **
\*                                                                      */

package squants.space

import squants._
import squants.photo.{ LuminousFlux, Lumens, LuminousIntensity }
import squants.energy.Watts
import squants.radio.RadiantIntensity

/**
 * @author  garyKeorkunian
 * @since   0.1
 *
 * @param value value in [[squants.space.SquaredRadians]]
 */
final class SolidAngle private (val value: Double) extends Quantity[SolidAngle] {

  def valueUnit = SquaredRadians

  def *(that: LuminousIntensity): LuminousFlux = Lumens(toSquaredRadians * that.toCandelas)
  def *(that: RadiantIntensity): Power = Watts(toSquaredRadians * that.toWattsPerSteradian)

  def toSquaredRadians = value
  def toSteradians = value
}

object SolidAngle {
  private[space] def apply[A](n: A)(implicit num: Numeric[A]) = new SolidAngle(num.toDouble(n))
}

trait SolidAngleUnit extends UnitOfMeasure[SolidAngle]

object SquaredRadians extends SolidAngleUnit with ValueUnit {
  def apply[A](n: A)(implicit num: Numeric[A]) = SolidAngle(n)
  val symbol = "sr"
}

object SolidAngleConversions {
  lazy val squaredRadian = SquaredRadians(1)
  lazy val steradian = SquaredRadians(1)

  implicit class SolidAngleConversions[A](n: A)(implicit num: Numeric[A]) {
    def squaredRadians = SquaredRadians(n)
    def steradians = SquaredRadians(n)
  }

  implicit object SolidAngleNumeric extends AbstractQuantityNumeric[SolidAngle](SquaredRadians)
}

