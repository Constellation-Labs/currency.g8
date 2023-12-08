package $package$.l0

import cats.effect.IO
import org.tessellation.BuildInfo
import org.tessellation.currency.dataApplication.{BaseDataApplicationL0Service}
import org.tessellation.currency.l0.CurrencyL0App
import org.tessellation.schema.cluster.ClusterId
import org.tessellation.security.SecurityProvider

import java.util.UUID

  object Main
    extends CurrencyL0App(
      "$name;format="lower,hyphen"$-l0",
      "$name$ L0 node",
      ClusterId(UUID.fromString("517c3a05-9219-471b-a54c-21b7d72f4ae5")),
      version = BuildInfo.version
    ) {
    override def dataApplication: Option[Resource[IO, BaseDataApplicationL0Service[IO]]] = None

    override def rewards(implicit sp: SecurityProvider[IO]) = None
  }
