package utils

import java.time.{ZoneId, ZonedDateTime}

import org.scalacheck.{Arbitrary, Gen}

object DateTimeUtil {
  val zone: ZoneId = ZoneId.of("Asia/Tokyo")

  def now(): ZonedDateTime = ZonedDateTime.now(zone)

  implicit val timeArbitrary: Arbitrary[ZonedDateTime] = Arbitrary {
    for {
      hour <- Gen.choose(0, 23)
      min <- Gen.choose(0, 59)
      sec <- Gen.choose(0, 59)
    } yield {
      now().withHour(hour).withMinute(min).withSecond(sec)
    }
  }
}
