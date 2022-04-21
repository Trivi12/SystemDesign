package TpUno

import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.collections.ArrayList


val partnerList = ArrayList<Partner>()

fun main(args: Array<String>){


    val partner1 = Partner("43493151", "Matias", "Trivisonno", "04/05/2001", "Ecuador 444",
        "San Jorge", "3401408270", "matitarufi@gmail.com", "0", "+",
        "NO", "NO", "Activo", "trivi123","Debito")

    partnerList.add(partner1)

    partner1.setDonation("25/03/2022",dni= partner1.dni)  //registrar donacion del socio
    partner1.setShare(partner1.category,partner1.paymentMethod,"31/04/2022",partner1.dni) //registrar una nueva cuota

    val share = Share(1000.0,"Debito","01/02/2022","Pago",partner1.dni)  //instanciar cuota vieja
    partner1.shareList.add(share)

    val option = menu()

    when(option){

        1 -> {signIn()}
        2 -> {logIn(partner1)}
        3 -> {liquidateShares()}
        4 -> {generatePetition()}
    }
}

//Menu de inicio
fun menu():Int{

    var option:Int

    println("\n\n---BIENVENIDO/A---")
    print("1.Registrarse \n2.Iniciar Sesion\n3.Liquidar Cuotas\n4.Generar una nueva peticion\n")

    do {
        option = readLine()!!.toInt()
    }while (option != 1 && option != 2 && option !=3 && option != 4)

    return  option
}

//Inicio de sesion con usuario registrado
fun logIn(partner: Partner){

    print("DNI: ")
    val dni = readLine().toString()
    print("Contraseña: ")
    val password = readLine().toString()

    if(dni != partner.dni || password != partner.password){
        println("¡Datos incorrectos! Vuelve a intentarlo")
        logIn(partner)
    }
    else{
        home(partner)
    }
}

//Home de la app con la sesion iniciada
fun home(partner: Partner){

    var option : Int
    println("\n\n---MENU PRINCIPAL---")
    println("Usuario: ${partner.name} ${partner.surname} (${partner.category})")
    print("1.Historial de donaciones \n2.Cuotas\n")

    do {
        option = readLine()!!.toInt()
    }while (option != 1 && option != 2)

    when(option){

        1 -> {
            println("|HISTORIAL DE DONACIONES|")
            val donationList = partner.getDonations()
            if(donationList.isEmpty()){
                println("No tiene donaciones registradas")
            }
            else{
                for (donation in donationList){
                    println("[-Fecha: ${donation.date}\n")
                }
            }
            println("0.Volver al menu principal")
            val go = readLine()!!.toInt()
            if(go == 0){
                home(partner)
            }

        }

        2 -> {
            println("|CUOTAS|")
            val shareList = partner.getShares()
            for(share in shareList!!){
                println("[-Precio: ${share.price}\n-Estado: ${share.state}\n-Metodo de pago: ${share.paymentMethod}]")
            }
            println("0.Volver al menu principal")
            val go = readLine()!!.toInt()
            if(go == 0){
                home(partner)
            }
        }
    }

}

//Registrar nuevo socio
fun signIn(){

    println("INGRESE SUS DATOS PERSONALES")

    print("Nombre: ")
    val name = readLine().toString()

    print("Apellido: ")
    val surname = readLine().toString()

    print("DNI: ")
    val dni = readLine().toString()

    print("Fecha de nacimiento (dd/mm/aaaa): ")
    val birthday = readLine().toString()

    print("Domicilio: ")
    val home = readLine().toString()

    print("Localidad: ")
    val location = readLine().toString()

    print("Telefono: ")
    val phone = readLine().toString()

    print("Email: ")
    val email = readLine().toString()

    print("Grupo sanguineo: ")
    val bloodGroup = readLine().toString()

    print("Factor sanguineo: ")
    val bloodFactor = readLine().toString()

    val disease:String
    var flagOne:Int
    do {
        print("¿Posee alguna enfermad cronica?: (0.NO 1.SI): ")
        flagOne = readLine()!!.toInt()
    }while (flagOne != 0 && flagOne != 1)

    if(flagOne == 0){
        disease = "NO"
    }
    else{
        disease = "SI"
    }


    val medication:String
    var flagTwo:Int
    do{
        print("¿Toma alguna medicacion permanente?: (0.NO 1.SI): ")
        flagTwo = readLine()!!.toInt()
    }while(flagTwo != 0 && flagTwo != 1)

    if (flagTwo == 0){
        medication = "NO"
    }
    else{
        medication ="SI"
    }

    val paymenMethod : String
    var flagThree:Int
    do {
        print("Elija un metodo de pago para las cuotas mensuales(1.Efectivo, 2.Debito, 3.Credito): " )
        flagThree = readLine()!!.toInt()
    }while (flagThree != 1 && flagThree != 2 && flagThree != 3)

    when(flagThree){
        1 -> {paymenMethod = "Efectivo"}
        2 -> {paymenMethod = "Debito"}
        3 -> {paymenMethod = "Credito"}
        else -> {paymenMethod = null.toString()
        }
    }

    var validation:String
    var password:String

    do {
        print("\nCrea una contraseña: ")
        password = readLine().toString()
        print("Vuelve a ingresar la contraseña: ")
        validation = readLine().toString()
        if(password == validation){
            print("¡Contraseña creada exitosamente!")
        }
        else{
            print("Las contraseñas no coinciden, vuelve a intentarlo")
        }
    }while (password != validation)

    val day = "${birthday[0]}${birthday[1]}".toInt()
    val month = "${birthday[3]}${birthday[4]}".toInt()
    val year = "${birthday[6]}${birthday[7]}${birthday[8]}${birthday[9]}".toInt()

    val age = getAge(year,month,day)
    val category:String

    if(age in 19..56 && flagOne==0 && flagTwo == 0){
        category = "Activo"
    }
    else{
        category = "Pasivo"
    }

    val partner = Partner(dni, name, surname, birthday, home, location, phone, email, bloodGroup,
        bloodFactor, disease, medication, category, password, paymenMethod)

    partnerList.add(partner)

    partner.setShare(partner.category,paymenMethod,"31/04/2022",partner.dni)

    home(partner)
}

//Obtener edad del socio
fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
    return Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    ).years
}

//Liquidacion de cuotas
fun liquidateShares(){

    val cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, 1)
    val dueDate = cal.toString()

    for(partner in partnerList) {
        partner.setShare(partner.category, partner.paymentMethod,dueDate,partner.dni)
    }

    println("¡Cuotas liquidadas exitosamente!")

    menu()
}

//Generar una nueva peticion
fun generatePetition(){

    println("GENERAR UNA NUEVA PETICION")

    var requiredQuantity : Double
    do{
        print("Cantidad necesaria de sangre [Litros]: ")
        requiredQuantity = readLine()!!.toDouble()
    }while (requiredQuantity < 0)

    val date = LocalDate.now()

    val partnersNeeded = requiredQuantity / 0.50

    val petition = Petition(requiredQuantity, partnersNeeded.toInt(),date.toString())

    println("\n¡Peticion generada exitosamente!")
    println("Cantidad necesaria: ${requiredQuantity} L")
    println("Socios necesarios: ${partnersNeeded.toInt()}")
    println("Fecha: $date")

    val selectedPartners = definePartners(partnersNeeded.toInt())

    if(selectedPartners.size < partnersNeeded.toInt()){
        println("NO HAY SUFICIENTES SOCIOS DISPONIBLES PARA COMPLETAR LA DONACION")
        println("Faltan: ${partnersNeeded.toInt() - selectedPartners.size} socios")
        println("SOCIOS DISPONIBLES")
        for(partner in selectedPartners){
            println("${partner.name} ${partner.surname} - ${partner.dni}")
        }
    }
    else{
        for(partner in selectedPartners){
            println("${partner.name} ${partner.surname} - ${partner.dni}")

        }
    }

    menu()
}

//Definir lista de socios para donar
fun definePartners(partnersNeeded:Int) : ArrayList<Partner>{

    val selectedPartners = ArrayList<Partner>()

    for(partner in partnerList){

        if(partner.category == "Activo"){
            val partnerDonations = partner.getDonations()

            if(partnerDonations.isEmpty()){
                selectedPartners.add(partner)
            }
            else if(partnerDonations.size == 1){
                val lastDonationDate = partnerDonations.get(0).date

                val day = "${lastDonationDate[0]}${lastDonationDate[1]}".toInt()
                val month = "${lastDonationDate[3]}${lastDonationDate[4]}".toInt()
                val year = "${lastDonationDate[6]}${lastDonationDate[7]}${lastDonationDate[8]}${lastDonationDate[9]}".toInt()

                val diferenceMonths = getMonthDiference(year,month,day)
                if(diferenceMonths > 2){
                    selectedPartners.add(partner)
                }
            }
        }
    }

    while(selectedPartners.size > partnersNeeded){
        selectedPartners.removeLast()
    }

    return selectedPartners
}

//Obtener la diferencia de meses de la ultima donacion
fun getMonthDiference(year: Int, month: Int, dayOfMonth: Int): Int {
    return Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    ).months
}