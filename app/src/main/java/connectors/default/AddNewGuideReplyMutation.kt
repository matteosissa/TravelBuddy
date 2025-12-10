
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

package connectors.default



public interface AddNewGuideReplyMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      DefaultConnector,
      AddNewGuideReplyMutation.Data,
      AddNewGuideReplyMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val userId: com.google.firebase.dataconnect.OptionalVariable<Int?>,
    val tripId: com.google.firebase.dataconnect.OptionalVariable<Int?>,
    val text: com.google.firebase.dataconnect.OptionalVariable<String?>,
    val time: com.google.firebase.dataconnect.OptionalVariable<@kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.TimestampSerializer::class) com.google.firebase.Timestamp?>
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      @BuilderDsl
      public interface Builder {
        public var userId: Int?
        public var tripId: Int?
        public var text: String?
        public var time: com.google.firebase.Timestamp?
        
      }

      public companion object {
        @Suppress("NAME_SHADOWING")
        public fun build(
          
          block_: Builder.() -> Unit
        ): Variables {
          var userId: com.google.firebase.dataconnect.OptionalVariable<Int?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var tripId: com.google.firebase.dataconnect.OptionalVariable<Int?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var text: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var time: com.google.firebase.dataconnect.OptionalVariable<com.google.firebase.Timestamp?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
            override var userId: Int?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { userId = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var tripId: Int?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { tripId = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var text: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { text = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var time: com.google.firebase.Timestamp?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { time = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              userId=userId,tripId=tripId,text=text,time=time,
            )
          }
        }
      }
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    @kotlinx.serialization.SerialName("tripAnswer_upsert") val key: TripAnswerKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddNewGuideReply"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddNewGuideReplyMutation.ref(
  
    
  
    block_: AddNewGuideReplyMutation.Variables.Builder.() -> Unit = {}
  
): com.google.firebase.dataconnect.MutationRef<
    AddNewGuideReplyMutation.Data,
    AddNewGuideReplyMutation.Variables
  > =
  ref(
    
      AddNewGuideReplyMutation.Variables.build(
        
  
    block_
      )
    
  )

public suspend fun AddNewGuideReplyMutation.execute(
  
    
  
    block_: AddNewGuideReplyMutation.Variables.Builder.() -> Unit = {}
  
  ): com.google.firebase.dataconnect.MutationResult<
    AddNewGuideReplyMutation.Data,
    AddNewGuideReplyMutation.Variables
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
