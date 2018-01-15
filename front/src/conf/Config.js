import local from './Local.js'

export default class Config {
  constructor(env) {
    this.env = env;
    this.apiHost = location.hostname;
    this.apiPort = location.port;

    // environment
    if (env === 'vagrant') {
      // todo
    } else if (env === 'local') {
      Object.keys(local).forEach(key => this[key] = local[key]);
    } else {
      console.error('unknown environement: ' + env);
    }
  }
}