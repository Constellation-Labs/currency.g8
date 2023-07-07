package $package$.data-l1

import java.util.UUID
import org.tessellation.BuildInfo
import org.tessellation.currency.l1.CurrencyL1App
import org.tessellation.schema.cluster.ClusterId

object Main
    extends CurrencyL1App(
      "$name;format="lower,hyphen"$-l1",
      "$name$ L1 data node",
      ClusterId(UUID.fromString("517c3a05-9219-471b-a54c-21b7d72f4ae5")),
      version = BuildInfo.version
    ) {

}
