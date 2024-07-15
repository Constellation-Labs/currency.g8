package $package$.data_l1

import java.util.UUID
import org.tessellation.BuildInfo
import org.tessellation.currency.l1.CurrencyL1App
import org.tessellation.schema.cluster.ClusterId
import org.tessellation.schema.semver.{MetagraphVersion, TessellationVersion}

object Main
    extends CurrencyL1App(
      "$name;format="lower,hyphen"$-data_l1",
      "$name$ data L1 data node",
      ClusterId(UUID.fromString("517c3a05-9219-471b-a54c-21b7d72f4ae5")),
      tessellationVersion = TessellationVersion.unsafeFrom(BuildInfo.version),
      metagraphVersion = MetagraphVersion.unsafeFrom(BuildInfo.version)
    ) {
    /*
    * To implement a data-l1 layer, you need to override the dataApplication function in CurrencyL1App:
    * 
    * override def dataApplication: Option[Resource[IO, BaseDataApplicationL1Service[IO]]]
    *
    * By default, this function returns None. If you do not provide an implementation, it will not function as a data layer,
    * and will instead operate as a normal currency layer.
    */

}
