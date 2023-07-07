import Dependencies._
import sbt._

ThisBuild / organization := "$package$"
ThisBuild / scalaVersion := "2.13.10"
ThisBuild / evictionErrorLevel := Level.Warn

ThisBuild / assemblyMergeStrategy := {
  case "logback.xml"                                       => MergeStrategy.first
  case x if x.contains("io.netty.versions.properties")     => MergeStrategy.discard
  case PathList(xs @ _*) if xs.last == "module-info.class" => MergeStrategy.first
  case x =>
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    oldStrategy(x)
}

lazy val root = (project in file(".")).
  settings(
    name := "$name;format="lower,hyphen"$"
  ).aggregate(currencyL0, currencyL1 $if(include_data_l1.truthy)$, dataL1$endif$)

lazy val currencyL1 = (project in file("modules/l1"))
  .enablePlugins(AshScriptPlugin)
  .enablePlugins(BuildInfoPlugin)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "$name;format="lower,hyphen"$-currency-l1",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "$package$.l1",
    resolvers += Resolver.mavenLocal,
    resolvers += Resolver.githubPackages("abankowski", "http-request-signer"),
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      CompilerPlugin.kindProjector,
      CompilerPlugin.betterMonadicFor,
      CompilerPlugin.semanticDB,
      Libraries.tessellationDAGL1,
      Libraries.tessellationSDK,
      Libraries.tessellationShared,
      Libraries.tessellationCurrencyL1
    )
  )

lazy val currencyL0 = (project in file("modules/l0"))
  .enablePlugins(AshScriptPlugin)
  .enablePlugins(BuildInfoPlugin)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "$name;format="lower,hyphen"$-currency-l0",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "$package$.l0",
    resolvers += Resolver.mavenLocal,
    resolvers += Resolver.githubPackages("abankowski", "http-request-signer"),
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      CompilerPlugin.kindProjector,
      CompilerPlugin.betterMonadicFor,
      CompilerPlugin.semanticDB,
      Libraries.declineRefined,
      Libraries.declineCore,
      Libraries.declineEffect,
      Libraries.tessellationKernel,
      Libraries.tessellationDAGL1,
      Libraries.tessellationSDK,
      Libraries.tessellationShared,
      Libraries.tessellationKeytool,
      Libraries.tessellationCurrencyL0
    )
  )

$if(include_data_l1.truthy)$
lazy val dataL1 = (project in file("modules/data-l1"))
  .enablePlugins(AshScriptPlugin)
  .enablePlugins(BuildInfoPlugin)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "$name;format="lower,hyphen"$-data-l1",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "$package$.data-l1",
    resolvers += Resolver.mavenLocal,
    resolvers += Resolver.githubPackages("abankowski", "http-request-signer"),
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      CompilerPlugin.kindProjector,
      CompilerPlugin.betterMonadicFor,
      CompilerPlugin.semanticDB,
      Libraries.tessellationDAGL1,
      Libraries.tessellationSDK,
      Libraries.tessellationShared,
      Libraries.tessellationCurrencyL1
    )
  )
$endif$