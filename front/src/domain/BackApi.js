import request from 'superagent';

export default class BackApi {
  constructor(host, port) {
    this.host = host;
    this.port = port;
  }

  urlOf(path) {
    return `http://${this.host}:${this.port}${path}`;
  }

  getRequest(path) {
    return new Promise((resolve, reject) => {
      return request.get(this.urlOf(path))
        .end((err, res) => {
          if (!res.ok) {
            console.error(err);
            reject(`Back API Failed: path=${path} res=${res.body}`)
          } else {
            resolve(res.body.data)
          }
        });
    });
  }

  say() {
    return this.getRequest("/api/v1/say");
  }
}
