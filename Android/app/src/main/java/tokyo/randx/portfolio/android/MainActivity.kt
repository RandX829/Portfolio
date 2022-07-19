/*
 * Copyright 2022 RandX <010and1001@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package tokyo.randx.portfolio.android

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  // index
  private val list = arrayOf("NFC(Reader)", "【WIP】NFC(HCE)",
    "【WIP】Bluetooth(BLE)", "【WIP】Bluetooth(Classic)",
    "【WIP】Wi-Fi")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Display index
    val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
    val listView: ListView = findViewById(R.id.lv_list)
    listView.adapter = arrayAdapter

    // click listener
    listView.setOnItemClickListener { _, _, position, _ ->
      when(position) {
        0 -> {
//        NFC(Reader)
          val intent = Intent(applicationContext, NfcActivity::class.java)
          startActivity(intent)
        }
        else ->
          Toast.makeText(this, "COMING SOON...", Toast.LENGTH_SHORT).show()
      }
    }

  }

}