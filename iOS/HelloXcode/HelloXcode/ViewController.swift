//
//  ViewController.swift
//  HelloXcode
//
//  Created by yesunsong on 2017/3/28.
//  Copyright © 2017年 yesunsong. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var userOutput: UILabel!
    @IBOutlet weak var userInput: UITextField!
    
    @IBAction func setOutput(_ sender: AnyObject) {
        userOutput.text = userInput.text;
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
//        var myMessage:UILabel
//        myMessage = UILabel(frame:CGRect(x:30.0,y:50.0,width:300.0,height:50.0))
//        myMessage.font = UIFont.systemFont(ofSize: 48.0)
//        myMessage.text = "Hello World"
//        myMessage.textColor = UIColor(patternImage:UIImage(named:"Background")!)
//        view.addSubview(myMessage)
    }

    //MARK: 
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

