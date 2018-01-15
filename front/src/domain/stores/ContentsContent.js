import { extendObservable, action } from 'mobx';

export default class ContentsContent {
  constructor(context) {
    extendObservable(this,{
      content: "",
      error: null,
      say: action(() =>  {
        context.api.say()
          .then(data => {
            this.content = data;
            this.error = null;
          }).catch((err) => this.error = err)
      }),
    });
  }
}

