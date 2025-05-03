
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


import kotlinx.coroutines.flow.filterNotNull as _flow_filterNotNull
import kotlinx.coroutines.flow.map as _flow_map


public interface AllTripsByLocationQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      DefaultConnector,
      AllTripsByLocationQuery.Data,
      AllTripsByLocationQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val siteName:
    com.google.firebase.dataconnect.OptionalVariable<String?>,
    val countryName:
    com.google.firebase.dataconnect.OptionalVariable<String?>
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      @BuilderDsl
      public interface Builder {
        public var siteName: String?
        public var countryName: String?
        
      }

      public companion object {
        @Suppress("NAME_SHADOWING")
        public fun build(
          
          block_: Builder.() -> Unit
        ): Variables {
          var siteName: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var countryName: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
            override var siteName: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { siteName = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var countryName: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { countryName = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              siteName=siteName,countryName=countryName,
            )
          }
        }
      }
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val trips:
    List<TripsItem>
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class TripsItem(
  
    val id:
    Int,
    val date:
    com.google.firebase.Timestamp,
    val locationCity:
    String?,
    val locationCountry:
    String?,
    val activities:
    List<String>?,
    val durationDays:
    Int?,
    val budget:
    Int?,
    val description:
    String?,
    val user:
    User
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class User(
  
    val id:
    Int,
    val name:
    String?
  ) {
    
    
  }
      
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "allTripsByLocation"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AllTripsByLocationQuery.ref(
  
    
  
    block_: AllTripsByLocationQuery.Variables.Builder.() -> Unit
  
): com.google.firebase.dataconnect.QueryRef<
    AllTripsByLocationQuery.Data,
    AllTripsByLocationQuery.Variables
  > =
  ref(
    
      AllTripsByLocationQuery.Variables.build(
        
  
    block_
      )
    
  )

public suspend fun AllTripsByLocationQuery.execute(
  
    
  
    block_: AllTripsByLocationQuery.Variables.Builder.() -> Unit
  
  ): com.google.firebase.dataconnect.QueryResult<
    AllTripsByLocationQuery.Data,
    AllTripsByLocationQuery.Variables
  > =
  ref(
    
      
  
    block_
    
  ).execute()


  public fun AllTripsByLocationQuery.flow(
    
      
  
    block_: AllTripsByLocationQuery.Variables.Builder.() -> Unit
    
    ): kotlinx.coroutines.flow.Flow<AllTripsByLocationQuery.Data> =
    ref(
        
          
  
    block_
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }


// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
