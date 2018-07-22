const Redux = require('../libs/redux.js')
const combineReducers = Redux.combineReducers
const todos = require('./todos.js')
const myApp = combineReducers({
  todos
})

module.exports = myApp