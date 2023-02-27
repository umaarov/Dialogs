package uz.umarov.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import uz.umarov.dialogs.databinding.ActivityMainBinding
import uz.umarov.dialogs.databinding.ItemDialogBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("ShowToast", "SuspiciousIndentation", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alertDialog.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Alert")
            dialog.setMessage("ALert Dialog")

            dialog.setNeutralButton("Ok"
            ) { _, _ ->
                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
            }
            dialog.setPositiveButton("Yes"){ _, _ ->
                Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
            }
            dialog.setNegativeButton("No"
            ) { _, _ ->
                Toast.makeText(this@MainActivity, "No", Toast.LENGTH_SHORT)
                    .show()
            }
            dialog.show()
        }

        binding.customDialog.setOnClickListener {
            val customDialog = AlertDialog.Builder(this).create()
            val itemDialog = ItemDialogBinding.inflate(layoutInflater)

            customDialog.setView(itemDialog.root)
            customDialog.show()
        }

        binding.fragmentDialog.setOnClickListener {
            val myDialogFragment = DialogFragment()
            myDialogFragment.show(supportFragmentManager, myDialogFragment.toString())
        }

        binding.timePicker.setOnClickListener {
            val date = Date()
            TimePickerDialog(this,
                { _, hourOfDay, minute ->
                    Toast.makeText(this@MainActivity,
                        "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                }, date.hours, date.minutes, true)
                .show()
        }
        binding.datePicker.setOnClickListener {
            val date = Date()
            val dateDialog = DatePickerDialog(this,
                { _, year, month, dayOfMonth -> Toast.makeText(this@MainActivity,
                    "$year/$month/$dayOfMonth", Toast.LENGTH_SHORT).show() },
                date.year,date.month-1,date.day)
            dateDialog.show()
        }

        binding.snackbar.setOnClickListener {
            val snackbar = Snackbar.make(binding.root, "snackbar", Snackbar.LENGTH_LONG)
            snackbar.show()
        }


        binding.bottomSheet.setOnClickListener {

            val dialog = BottomSheetDialog(this)

            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)

            dialog.setContentView(view)

            dialog.show()
        }

    }
}