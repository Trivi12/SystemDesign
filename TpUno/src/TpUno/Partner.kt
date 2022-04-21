package TpUno


class Partner(val dni:String,
              val name: String,
              val surname:String,
              val birthday:String,
              val home:String,
              val location:String,
              val phone:String,
              val email:String,
              val bloodGroup:String,
              val bloodFactor:String,
              val disease:String,
              val medication:String,
              val category:String?,
              val password:String,
              val paymentMethod: String) {

    var donationList= ArrayList<Donation>()
    var shareList = ArrayList<Share>()

    //Registar una nueva donacion
    fun setDonation(date: String, dni: String){
        val donation = Donation(date, dni)
        donationList.add(donation)
    }

    //Obtener donaciones de un socio
    fun getDonations(): ArrayList<Donation> {
        val partnerDonations = ArrayList<Donation>()

        for(donation in donationList){
            if (dni == donation.dni){
                partnerDonations.add(donation)
            }
        }
        return partnerDonations
    }

    //Registrar un nueva cuota
    fun setShare(category: String?,paymentMethod:String,dueDate:String,dni: String){
        if(category == "Activo"){
            val share = Share(1000.0,paymentMethod,dueDate,"Pendiente",dni)
            shareList.add(share)

        }
        else if(category == "Pasivo"){
            val share = Share(600.0,paymentMethod,dueDate,"Pendiente",dni)
            shareList.add(share)
        }
    }

    //Obtener cuotas de un socio
    fun getShares(): ArrayList<Share>?{
        val partnerShares=ArrayList<Share>()

        for(share in shareList){
            if(dni == share.dni){
                partnerShares.add(share)
            }
        }
        return partnerShares
    }

    fun setPartner(dni:String,name: String,surname:String,birthday:String,home:String,location:String,phone:String,email:String,
                   bloodGroup:String,bloodFactor:String,disease:String,medication:String,category:String?,
                   password:String,paymentMethod: String):Partner{

        val partner = Partner(dni,name,surname, birthday, home, location, phone, email, bloodGroup,
            bloodFactor, disease, medication, category, password, paymentMethod)

        return partner
    }
}