import Config from '../conf/Config'
import BackApi from './BackApi'

export default class AppContext {
  constructor(env) {
    this.conf = new Config(env);
    this.api = new BackApi(this.conf.apiHost, this.conf.apiPort);
  }
}
