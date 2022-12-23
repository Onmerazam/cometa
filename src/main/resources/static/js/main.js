
Vue.component('messages-list', {
  props: ['messages'],
  template: '<div>List</div>'
})

var app = new Vue({
  el: '#app',
  template : '<messages-list :message="messages" />',
  data: {
    messages: [
        {id: '123', text: 'wow'},
        {id: '3', text: 'wo123w'},
        {id: '13', text: 'wogrgrgrgw'}
    ]
  }
});