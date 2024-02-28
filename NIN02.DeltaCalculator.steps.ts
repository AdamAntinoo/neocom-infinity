// import { Before, Given, Then, When } from '@cucumber/cucumber'
import expect from 'expect'
import { NeoComWorld } from '../neocom-world'
import { json } from 'stream/consumers'

// Before({ tags: '@' }, async function (this: NeoComWorld) {
//   // this.foo = true
// })
// Given('a base asset list of type {int}', function (this: NeoComWorld, listType: number) {
//   console.info('World:'+JSON.stringify(this))
//   this.setInitialList([
//     {
//       "is_singleton": false,
//       "item_id": 100001,
//       "location_flag": "AutoFit",
//       "location_id": 1035124094434,
//       "location_type": "item",
//       "quantity": 1000,
//       "type_id": 101
//     },
//     {
//       "is_singleton": false,
//       "item_id": 100001,
//       "location_flag": "AutoFit",
//       "location_id": 1035124094434,
//       "location_type": "item",
//       "quantity": 1000,
//       "type_id": 102
//     },
//     {
//       "is_singleton": false,
//       "item_id": 100001,
//       "location_flag": "AutoFit",
//       "location_id": 1035124094434,
//       "location_type": "item",
//       "quantity": 1000,
//       "type_id": 103
//     }
//   ])
// })
