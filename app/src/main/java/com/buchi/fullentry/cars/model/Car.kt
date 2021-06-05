package com.buchi.fullentry.cars.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val id: String,
    val title: String?,
    val imageUrl: String?,
    val year: Long?,
    val city: String?,
    val state: String?,
    val gradeScore: Double?,
    val sellingCondition: String?,
    val hasWarranty: Boolean?,
    val marketplacePrice: Long?,
    val marketplaceOldPrice: Long?,
    val hasFinancing: Boolean?,
    val mileage: Long?,
    val mileageUnit: String?,
    val installment: Int?,
    val depositReceived: Boolean?,
    val loanValue: Int?,
    val websiteUrl: String?,
    val bodyTypeId: String?,
    val sold: Boolean?,
    val hasThreeDImage: Boolean?,
    // Todo Add stats

    val insured: Boolean?,
    val vin: String?,
    val licensePlate: String?,
    val engineNumber: String?,
    val price: Long?,
    val createdBy: String?,
    val marketplaceVisible: Boolean?,
    val marketplaceVisibleDate: String?,
    val isFeatured: Boolean?,
    val reasonForSelling: String?,
    val ownerId: String?,
    val model: CarModel?,
    val popular: Boolean?,
    val country: String?,
    val address: String?,
    val ownerType: String?,
    val damageMedia: List<InspectionItems>? = null
) : Parcelable

/*
{
  "features": [],
  "carFeatures": [],
  "modelFeatures": [],
  "damageMedia": [
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "dashboard/fittings",
          "response": "good"
        },
        {
          "medias": [],
          "name": "door fittings rim",
          "response": "good"
        },
        {
          "medias": [
            {}
          ],
          "name": "seat upholstery",
          "comment": "Scratch on seat upholstery",
          "response": "bad",
          "condition": "scratched"
        },
        {
          "medias": [],
          "name": "roof upholstery",
          "response": "good"
        },
        {
          "medias": [],
          "name": "arm or head rest upholstery",
          "response": "good"
        },
        {
          "medias": [],
          "name": "centre console",
          "response": "good"
        },
        {
          "medias": [],
          "name": "gear lever",
          "response": "good"
        },
        {
          "medias": [
            {}
          ],
          "name": "steering wheel",
          "comment": " fade on steering wheel",
          "response": "bad",
          "condition": "faded"
        },
        {
          "medias": [
            {}
          ],
          "name": "interior floor carpet",
          "comment": "Dirty",
          "response": "bad",
          "condition": "dirty"
        },
        {
          "medias": [],
          "name": "door handle inner",
          "response": "good"
        },
        {
          "medias": [],
          "name": "seat belt",
          "response": "good"
        },
        {
          "medias": [],
          "name": "inner mirror",
          "response": "good"
        },
        {
          "medias": [],
          "name": "sunvisors",
          "response": "good"
        },
        {
          "medias": [],
          "name": "sunvisors",
          "response": "good"
        },
        {
          "medias": [],
          "name": "boot trim or fittings",
          "response": "good"
        }
      ],
      "name": "Interior",
      "comment": "Scratch on seat upholstery,fade on steering wheel,dirty on floor carpet"
    },
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "door frt lh",
          "response": "good"
        },
        {
          "medias": [],
          "name": "feender frt lh",
          "response": "good"
        },
        {
          "medias": [],
          "name": "bonnet",
          "response": "good"
        },
        {
          "medias": [],
          "name": "front windshield",
          "response": "good"
        },
        {
          "medias": [
            {}
          ],
          "name": "bumber front",
          "comment": "Scratch ",
          "response": "bad",
          "condition": "scratched"
        },
        {
          "medias": [],
          "name": "radiation core support",
          "response": "good"
        },
        {
          "medias": [],
          "name": "fender frt rh",
          "response": "good"
        },
        {
          "medias": [],
          "name": "door frt rh",
          "response": "good"
        },
        {
          "medias": [],
          "name": "back door rh",
          "response": "good"
        },
        {
          "medias": [
            {}
          ],
          "name": "bumper rear",
          "comment": "Scratch on bumper rear",
          "response": "bad",
          "condition": "scratched"
        },
        {
          "medias": [],
          "name": "boot or trunk or tail gate",
          "response": "good"
        },
        {
          "medias": [],
          "name": "fender rear rh",
          "response": "good"
        },
        {
          "medias": [],
          "name": "fender rear lh",
          "response": "good"
        },
        {
          "medias": [],
          "name": "back door lh",
          "response": "good"
        },
        {
          "medias": [],
          "name": "body frame",
          "response": "good"
        },
        {
          "medias": [],
          "name": "cowl top fittings",
          "response": "good"
        },
        {
          "medias": [],
          "name": "chassis",
          "response": "good"
        },
        {
          "medias": [],
          "name": "rear windshield",
          "response": "good"
        },
        {
          "medias": [],
          "name": "underbody",
          "response": "good"
        },
        {
          "medias": [],
          "name": "side mirror",
          "response": "good"
        },
        {
          "medias": [],
          "name": "door glasses",
          "response": "good"
        },
        {
          "medias": [],
          "name": "outer handles",
          "response": "good"
        },
        {
          "medias": [],
          "name": "door locks",
          "response": "good"
        },
        {
          "medias": [],
          "name": "door rubbers/mouldings",
          "response": "good"
        },
        {
          "medias": [],
          "name": "window winder mechanism",
          "response": "good"
        },
        {
          "medias": [],
          "name": "trunk floor panels",
          "response": "good"
        }
      ],
      "name": "Exterior",
      "comment": "Scratch on car body"
    },
    {
      "inspectionItems": [
        {
          "medias": [
            {}
          ],
          "name": "oil leakage - valve cover",
          "comment": "Mild leakage",
          "response": "bad",
          "condition": "mild"
        },
        {
          "medias": [],
          "name": "oil leakage - timing cover",
          "response": "good"
        },
        {
          "medias": [],
          "name": "oil leakage - oil sump",
          "response": "good"
        },
        {
          "medias": [],
          "name": "oil leakage - cr. shaft seal back",
          "response": "good"
        },
        {
          "medias": [],
          "name": "coolant",
          "response": "good"
        },
        {
          "medias": [],
          "name": "reservoir",
          "response": "good"
        },
        {
          "medias": [],
          "name": "radiator hose",
          "response": "good"
        },
        {
          "medias": [],
          "name": "radiator caps",
          "response": "good"
        },
        {
          "medias": [],
          "name": "engine mounts",
          "response": "good"
        },
        {
          "medias": [
            {}
          ],
          "name": "drive belt/pulley",
          "comment": "Mild Pulley noise",
          "response": "bad",
          "condition": "noisy"
        },
        {
          "medias": [
            {}
          ],
          "name": "wiring harness",
          "comment": "Fan connected directly",
          "response": "bad",
          "condition": "tempered, fan direct"
        },
        {
          "medias": [
            {}
          ],
          "name": "noise - tappet",
          "comment": "Mild tappet sound",
          "response": "bad",
          "condition": "mild"
        },
        {
          "medias": [],
          "name": "noise - timing chain/belt",
          "response": "good"
        },
        {
          "medias": [],
          "name": "sludge",
          "response": "good"
        }
      ],
      "name": "Engine",
      "comment": "Wire tampered with fan connected directly,mild tappet sound, mild pulley sound"
    },
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "oxygen sensors",
          "response": "good"
        },
        {
          "medias": [],
          "name": "exhaust tail type",
          "response": "good"
        }
      ],
      "name": "Exhaust System",
      "comment": "Ok"
    },
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "clutch operations",
          "response": "good"
        },
        {
          "medias": [],
          "name": "oil leakage - housing",
          "response": "good"
        },
        {
          "medias": [
            {}
          ],
          "name": "delay in shifting",
          "comment": "Slight delay",
          "response": "bad",
          "condition": "slight"
        },
        {
          "medias": [],
          "name": "jerk/shifting shock",
          "response": "good"
        }
      ],
      "name": "Transmission & Clutch",
      "comment": "Slight delay"
    },
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "compressor",
          "response": "good"
        },
        {
          "medias": [],
          "name": "electrical wiring/fan",
          "response": "good"
        }
      ],
      "name": "Air conditioning system",
      "comment": "Ac chilling"
    },
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "abs",
          "response": "good"
        },
        {
          "medias": [],
          "name": "front brake pads",
          "response": "good"
        }
      ],
      "name": "Brakes",
      "comment": "Ok"
    },
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "height control",
          "response": "n/a"
        },
        {
          "medias": [],
          "name": "air suspension",
          "response": "n/a"
        }
      ],
      "name": "Suspension & Steering",
      "comment": "Ok"
    },
    {
      "inspectionItems": [
        {
          "medias": [],
          "name": "instrument cluster",
          "response": "good"
        },
        {
          "medias": [],
          "name": "map/cabin lamps",
          "response": "good"
        }
      ],
      "name": "Electricals",
      "comment": "Ok"
    }
  ],
  "id": "pgHpI6GIH",
  "year": 2000,
  "insured": false,
  "mileage": 9590,
  "vin": "JT6H*************",
  "licensePlate": "not registered",
  "engineNumber": "",
  "price": 2500000,
  "createdBy": "XjsMJBWxt",
  "marketplacePrice": 2515000,
  "marketplaceVisible": true,
  "marketplaceVisibleDate": "2021-06-01T08:56:07.297Z",
  "isFeatured": false,
  "reasonForSelling": "Business",
  "imageUrl": "https://storage.googleapis.com/img.autochek.africa/86d9fcfb-9caf-4cd8-b97b-7353c1acf2c4-IMG.jpg",
  "ownerId": "2p2ssWpu7",
  "model": {
    "modelFeatures": [],
    "id": 950,
    "name": "RX 300",
    "imageUrl": "",
    "wheelType": "2WD",
    "series": "RX Series",
    "make": {
      "id": 61,
      "name": "Lexus",
      "imageUrl": "https://storage.googleapis.com/img.autochek.africa/brands/lexus.svg"
    },
    "popular": false
  },
  "state": "Lagos",
  "country": "NG",
  "address": "Lagos",
  "carManagerId": "7bqlCM195",
  "ownerType": "individual",
  "transmission": "automatic",
  "fuelType": "petrol",
  "sellingCondition": "foreign",
  "bodyType": {
    "id": 3,
    "name": "SUV",
    "imageUrl": "https://storage.googleapis.com/img.autochek.africa/svg/Suv.svg"
  },
  "city": "Sangotedo, Lekki",
  "marketplaceOldPrice": 2515000,
  "createdAt": "2021-01-11T12:40:38.440Z",
  "updatedAt": "2021-06-01T08:56:07.334Z",
  "mileageUnit": "km",
  "hasWarranty": false,
  "hasFinancing": false,
  "interiorColor": "Cream",
  "exteriorColor": "White",
  "engineType": "6-cylinder(V6)",
  "gradeScore": 4.900000095367432,
  "installment": 0,
  "depositReceived": false,
  "isFirstOwner": true,
  "firstOwnerName": "",
  "firstOwnerPhone": "",
  "loanValue": 0,
  "websiteUrl": "https://autochek.africa/ng/car/rx-300-lexus-ref-pgHpI6GIH",
  "stats": {
    "webViewCount": 14,
    "webViewerCount": 13,
    "interestCount": 0,
    "testDriveCount": 0,
    "appViewCount": 6,
    "appViewerCount": 4,
    "processedLoanCount": 0
  },
  "sold": false,
  "hasThreeDImage": false
}
 */

/*

{
"id": "pdUCkqhrA",
"title": "Mercedes-Benz G 63 AMG",
"imageUrl": "https://media.autochek.africa/file/oaw1hocg.jpg",
"year": 2019,
"city": "Surulere",
"state": "Lagos",
"gradeScore": 5.0,
"sellingCondition": "new",
"hasWarranty": false,
"marketplacePrice": 135515008,
"marketplaceOldPrice": 135015008,
"hasFinancing": false,
"mileage": 10018,
"mileageUnit": "km",
"installment": 0,
"depositReceived": false,
"loanValue": 0,
"websiteUrl": "https://autochek.africa/ng/car/g-63 amg-mercedes-benz-ref-pdUCkqhrA",
"stats": {
"webViewCount": 33,
"webViewerCount": 31,
"interestCount": 0,
"testDriveCount": 0,
"appViewCount": 15,
"appViewerCount": 15,
"processedLoanCount": 0
},
"bodyTypeId": "3",
"sold": false,
"hasThreeDImage": false
}
        */