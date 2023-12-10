package com.example.unitconvertor

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConvertor(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("Result: ") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    val costumTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Magenta
        )

    fun convertUnits(){
        //elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result= (inputValueDouble*conversionFactor.value*100 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString();
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Text("Unit Convertor", style = costumTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        //Here goes what should happen when the value of TextField is changed
        },
            label = { Text("Enter Value: ")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box
          Box{
              //input button
              Button(onClick = { iExpanded = true}) {
                  Text(text = inputUnit)
                  Icon(Icons.Default.ArrowDropDown,
                     contentDescription = "Arrow Down" )
              }
              DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                  DropdownMenuItem(text = {
                      Text(text = "Centimeters")},
                      onClick = {
                          iExpanded = false;
                          inputUnit = "Centimeters"
                          conversionFactor.value = 0.01;
                          convertUnits()
                      }
                  )
                  DropdownMenuItem(text = {
                      Text(text = "Meters")},
                      onClick = {
                          iExpanded = false;
                          inputUnit = "Meters"
                          conversionFactor.value = 1.0;
                          convertUnits()
                      }
                  )
                  DropdownMenuItem(text = {
                      Text(text = "Feet")},
                      onClick = {
                          iExpanded = false;
                          inputUnit = "Feet"
                          conversionFactor.value = 0.3048
                          convertUnits()
                      }
                  )
                  DropdownMenuItem(text = {
                      Text(text = "Millimeters")},
                      onClick = {
                          iExpanded = false;
                          inputUnit = "Millimeters"
                          conversionFactor.value = 0.001;
                          convertUnits()
                      }
                  )
              }
          }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box{
                //output button
                Button(onClick = { oExpanded = true }) {

                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = {
                        Text(text = "Centimeters")},
                        onClick = {
                            oExpanded = false;
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01;
                            convertUnits() }
                    )
                    DropdownMenuItem(text = {
                        Text(text = "Meters")},
                        onClick = {
                            oExpanded = false;
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00;
                            convertUnits() }
                    )
                    DropdownMenuItem(text = {
                        Text(text = "Feet")},
                        onClick = { oExpanded = false;
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048;
                            convertUnits() }
                    )
                    DropdownMenuItem(text = {
                        Text(text = "Millimeters")},
                        onClick = { oExpanded = false;
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001;
                            convertUnits() }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = outputValue +" "+  outputUnit,
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConvertor()
}