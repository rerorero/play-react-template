package modules

import com.google.inject.AbstractModule

class ImplModule extends AbstractModule {
  override def configure() = {
    // TODO: inject service implements
    // bind(classOf[SomeService]).toInstance(
    //   new DefaultSomeService(SomeConfigure)
    // )
  }
}
