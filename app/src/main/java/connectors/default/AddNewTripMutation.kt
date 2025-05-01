
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



public interface AddNewTripMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      DefaultConnector,
      AddNewTripMutation.Data,
      AddNewTripMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val userId:
    com.google.firebase.dataconnect.OptionalVariable<Int?>,
    val activities:
    com.google.firebase.dataconnect.OptionalVariable<List<String>?>,
    val budget:
    com.google.firebase.dataconnect.OptionalVariable<Int?>,
    val date:
    com.google.firebase.dataconnect.OptionalVariable<com.google.firebase.Timestamp?>,
    val description:
    com.google.firebase.dataconnect.OptionalVariable<String?>,
    val durationDays:
    com.google.firebase.dataconnect.OptionalVariable<Int?>,
    val locationCity:
    com.google.firebase.dataconnect.OptionalVariable<String?>,
    val locationCountry:
    com.google.firebase.dataconnect.OptionalVariable<String?>
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      @BuilderDsl
      public interface Builder {
        public var userId: Int?
        public var activities: List<String>?
        public var budget: Int?
        public var date: com.google.firebase.Timestamp?
        public var description: String?
        public var durationDays: Int?
        public var locationCity: String?
        public var locationCountry: String?
        
      }

      public companion object {
        @Suppress("NAME_SHADOWING")
        public fun build(
          
          block_: Builder.() -> Unit
        ): Variables {
          var userId: com.google.firebase.dataconnect.OptionalVariable<Int?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var activities: com.google.firebase.dataconnect.OptionalVariable<List<String>?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var budget: com.google.firebase.dataconnect.OptionalVariable<Int?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var date: com.google.firebase.dataconnect.OptionalVariable<com.google.firebase.Timestamp?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var description: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var durationDays: com.google.firebase.dataconnect.OptionalVariable<Int?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var locationCity: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var locationCountry: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
            override var userId: Int?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { userId = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var activities: List<String>?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { activities = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var budget: Int?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { budget = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var date: com.google.firebase.Timestamp?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { date = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var description: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { description = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var durationDays: Int?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { durationDays = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var locationCity: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { locationCity = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var locationCountry: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { locationCountry = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              userId=userId,activities=activities,budget=budget,date=date,description=description,durationDays=durationDays,locationCity=locationCity,locationCountry=locationCountry,
            )
          }
        }
      }
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  @kotlinx.serialization.SerialName("trip_insert")
    val key:
    TripKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddNewTrip"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddNewTripMutation.ref(
  
    
  
    block_: AddNewTripMutation.Variables.Builder.() -> Unit
  
): com.google.firebase.dataconnect.MutationRef<
    AddNewTripMutation.Data,
    AddNewTripMutation.Variables
  > =
  ref(
    
      AddNewTripMutation.Variables.build(
        
  
    block_
      )
    
  )

public suspend fun AddNewTripMutation.execute(
  
    
  
    block_: AddNewTripMutation.Variables.Builder.() -> Unit
  
  ): com.google.firebase.dataconnect.MutationResult<
    AddNewTripMutation.Data,
    AddNewTripMutation.Variables
  > =
  ref(
    
      
  
    block_
    
  ).execute()



// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
