
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


import kotlinx.coroutines.flow.filterNotNull as _flow_filterNotNull
import kotlinx.coroutines.flow.map as _flow_map


public interface GetUserIdQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      DefaultConnector,
      GetUserIdQuery.Data,
      GetUserIdQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val username:
    com.google.firebase.dataconnect.OptionalVariable<String?>,
    val phoneNumber:
    com.google.firebase.dataconnect.OptionalVariable<String?>
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      @BuilderDsl
      public interface Builder {
        public var username: String?
        public var phoneNumber: String?
        
      }

      public companion object {
        @Suppress("NAME_SHADOWING")
        public fun build(
          
          block_: Builder.() -> Unit
        ): Variables {
          var username: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var phoneNumber: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
            override var username: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { username = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var phoneNumber: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { phoneNumber = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              username=username,phoneNumber=phoneNumber,
            )
          }
        }
      }
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val users:
    List<UsersItem>
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class UsersItem(
  @kotlinx.serialization.SerialName("id")
    val count:
    Int
  ) {
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "getUserId"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun GetUserIdQuery.ref(
  
    
  
    block_: GetUserIdQuery.Variables.Builder.() -> Unit
  
): com.google.firebase.dataconnect.QueryRef<
    GetUserIdQuery.Data,
    GetUserIdQuery.Variables
  > =
  ref(
    
      GetUserIdQuery.Variables.build(
        
  
    block_
      )
    
  )

public suspend fun GetUserIdQuery.execute(
  
    
  
    block_: GetUserIdQuery.Variables.Builder.() -> Unit
  
  ): com.google.firebase.dataconnect.QueryResult<
    GetUserIdQuery.Data,
    GetUserIdQuery.Variables
  > =
  ref(
    
      
  
    block_
    
  ).execute()


  public fun GetUserIdQuery.flow(
    
      
  
    block_: GetUserIdQuery.Variables.Builder.() -> Unit
    
    ): kotlinx.coroutines.flow.Flow<GetUserIdQuery.Data> =
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
