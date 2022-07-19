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

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.experimental.and

class NfcActivity : AppCompatActivity() {
  private val TAG = "NfcActivity"
  private lateinit var nfcAdapter: NfcAdapter
  private lateinit var pendingIntent: PendingIntent

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nfc)
    init()
  }

  override fun onResume() {
    super.onResume()
    //  Enable Foreground Dispatch
    nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
  }

  override fun onPause() {
    super.onPause()
    //  Disable Foreground Dispatch
    nfcAdapter.disableForegroundDispatch(this)
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    if (NfcAdapter.ACTION_TAG_DISCOVERED == intent?.action) {
      val tag = intent.getParcelableExtra<Parcelable>(NfcAdapter.EXTRA_TAG) as Tag?

//    Display Technologies
      val techList = tag!!.techList
      val tvTechnologies = findViewById<TextView>(R.id.tv_tag_technologies)
      tvTechnologies.text = resources.getString(R.string.nfc_tag_technologies_prefix, getTechnologies(techList))

//    Display Tag ID
      val tagIdBytes = tag.id
      val tvTagId = findViewById<TextView>(R.id.tv_tag_id)
      tvTagId.text = resources.getString(R.string.nfc_tag_id_prefix, byte2HexString(tagIdBytes))


    }
  }

  /**
   * convert bytes to formatted Hex String
   * eg. 01:23:45:9A:AB:CD:EF
   *
   * @param bytes bytes of Tag ID
   * @return formatted Hex String of Tag ID
   *
   */
  private fun byte2HexString(bytes: ByteArray): String? {
    val result = StringBuilder()

    for (i in bytes.indices) {
      result.append(String.format("%02X", bytes[i] and 0xFF.toByte()))
      if (i < bytes.size - 1) result.append(":")
    }

    return result.toString()
  }

  /**
   * get formatted technologies of the Tag
   * eg. NfcA,NfcF
   *
   * @param technologies Array of technologies
   * @return formatted technologies string of the Tag
   *
   */
  private fun getTechnologies(technologies: Array<String>): String? {
    val result = StringBuilder()

    for (i in technologies.indices) {
      result.append(technologies[i].substring(17))
      if (i < technologies.size - 1) result.append(",")
    }

    return result.toString()
  }

  /**
   * Check if NFC is ready
   *
   * @param nfcAdapter NFC Adapter
   * @return Result READY or NOT_SUPPORTED or NOT_ENABLED
   *
   */
  private fun isNfcAvailable(nfcAdapter: NfcAdapter?): Result {
    return if (nfcAdapter == null) {
      Log.d(TAG, resources.getString(R.string.nfc_not_supported))
      Result.NOT_SUPPORTED
    } else if (!nfcAdapter.isEnabled) {
      Log.d(TAG, resources.getString(R.string.nfc_not_enabled))
      Result.NOT_ENABLED
    } else {
      Log.d(TAG, resources.getString(R.string.nfc_ready))
      Result.READY
    }
  }

  /**
   * Result ENUM
   *
   */
  enum class Result {
    READY,
    NOT_SUPPORTED,
    NOT_ENABLED
  }

  /**
   * initiate NFC
   *
   */
  private fun init() {
    pendingIntent = PendingIntent.getActivity(
      applicationContext, 0,
      Intent(applicationContext, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
    )
    nfcAdapter = NfcAdapter.getDefaultAdapter(applicationContext)

    when (isNfcAvailable(nfcAdapter)) {
      Result.NOT_SUPPORTED -> {
        Toast.makeText(applicationContext, resources.getString(R.string.nfc_not_supported), Toast.LENGTH_SHORT).show()
      }
      Result.NOT_ENABLED -> {
        Toast.makeText(applicationContext, resources.getString(R.string.nfc_not_enabled), Toast.LENGTH_SHORT).show()
      }
      else -> {
        Toast.makeText(applicationContext, resources.getString(R.string.nfc_ready), Toast.LENGTH_SHORT).show()
      }
    }
  }
}