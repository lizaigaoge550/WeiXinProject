const {createStore, compose} = require('./libs/redux.js');

const reducer = require('./reducers/index.js')

function configureStore() {
  return createStore(reducer);
}

module.exports = configureStore;
