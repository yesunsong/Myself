//: Playground - noun: a place where people can play

import UIKit

var str:String = "Hello, playground"

var boo:Bool = false

for count in 0...49 {
 print("Count is \(count)")
}

var userAge:Int = 30
var useAageInDays:Int = userAge * 365

var users:[String:String] = ["good":"good","excellent":"excellent","great":"great"]

var firstName:String? = "Ye"
var lastName:String = "Sunsong"
var name:String = "my name is \(firstName) \(lastName)"
name =  name.replacingOccurrences(of: "name", with: " last name")
name = name.capitalized


//Foundation类==========================================================================
import Foundation

let url:NSURL = NSURL(string:"http://www.apple.com/hk/ipad-pro/")!
url.host

// MARK :
let view:UIView = UIView(frame:CGRect(x:0,y:0,width:100,height:100))
view.backgroundColor = UIColor.red
view.backgroundColor = UIColor.blue

var myMessage:UILabel
myMessage = UILabel(frame:CGRect(x:30.0,y:50.0,width:300.0,height:50.0))
myMessage.font = UIFont.systemFont(ofSize: 48.0)
myMessage.text = "Hello World"
myMessage.textColor = UIColor(patternImage:UIImage(named:"Background")!)

let date:NSDate = NSDate.distantFuture as NSDate
