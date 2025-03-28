//
//  SecondViewController.swift
//  Segway
//
//  Created by Kyle Kim on 10/29/19.
//  Copyright © 2019 Kyle Kim. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    @IBOutlet weak var myLabel: UILabel!
    var textPassedOver : String?
    override func viewDidLoad() {
        super.viewDidLoad()
        myLabel.text = textPassedOver
        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
