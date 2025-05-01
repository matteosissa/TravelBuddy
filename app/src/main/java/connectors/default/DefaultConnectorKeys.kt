
@file:kotlin.Suppress(
  "KotlinRedundantDiagnosticSuppress",
  "LocalVariableName",
  "MayBeConstant",
  "RedundantVisibilityModifier",
  "RemoveEmptyClassBody",
  "SpellCheckingInspection",
  "LocalVariableName",
  "unused",
)


@file:kotlinx.serialization.UseSerializers(
  
    com.google.firebase.dataconnect.serializers.TimestampSerializer::class,
  
)


package connectors.default


  @kotlinx.serialization.Serializable
  public data class SiteGuideKey(
  
    val siteName:
    String,
    val countryName:
    String
  ) {
    
    
  }

  @kotlinx.serialization.Serializable
  public data class SiteGuideListKey(
  
    val userId:
    Int,
    val siteSiteName:
    String,
    val siteCountryName:
    String
  ) {
    
    
  }

  @kotlinx.serialization.Serializable
  public data class TripAnswerKey(
  
    val tripId:
    Int,
    val userId:
    Int
  ) {
    
    
  }

  @kotlinx.serialization.Serializable
  public data class TripKey(
  
    val id:
    Int
  ) {
    
    
  }

  @kotlinx.serialization.Serializable
  public data class UserKey(
  
    val id:
    Int
  ) {
    
    
  }


// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
