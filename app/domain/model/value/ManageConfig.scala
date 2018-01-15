package domain.model.value

import com.typesafe.config.{Config, ConfigFactory}

class ManageConfig(config: Config) {
  val env = config.getString("env")
}

object ManageConfig extends ManageConfig(ConfigFactory.load())
