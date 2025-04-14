
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


public interface AllSitesAsGuideQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      DefaultConnector,
      AllSitesAsGuideQuery.Data,
      AllSitesAsGuideQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val userId:
    com.google.firebase.dataconnect.OptionalVariable<Int?>
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      @BuilderDsl
      public interface Builder {
        public var userId: Int?
        
      }

      public companion object {
        @Suppress("NAME_SHADOWING")
        public fun build(
          
          block_: Builder.() -> Unit
        ): Variables {
          var userId: com.google.firebase.dataconnect.OptionalVariable<Int?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
            override var userId: Int?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { userId = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              userId=userId,
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
  
    val guideSites:
    List<GuideSitesItem>
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class GuideSitesItem(
  
    val siteName:
    String,
    val countryName:
    String
  ) {
    
    
  }
      
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "allSitesAsGuide"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AllSitesAsGuideQuery.ref(
  
    
  
    block_: AllSitesAsGuideQuery.Variables.Builder.() -> Unit
  
): com.google.firebase.dataconnect.QueryRef<
    AllSitesAsGuideQuery.Data,
    AllSitesAsGuideQuery.Variables
  > =
  ref(
    
      AllSitesAsGuideQuery.Variables.build(
        
  
    block_
      )
    
  )

public suspend fun AllSitesAsGuideQuery.execute(
  
    
  
    block_: AllSitesAsGuideQuery.Variables.Builder.() -> Unit
  
  ): com.google.firebase.dataconnect.QueryResult<
    AllSitesAsGuideQuery.Data,
    AllSitesAsGuideQuery.Variables
  > =
  ref(
    
      
  
    block_
    
  ).execute()


  public fun AllSitesAsGuideQuery.flow(
    
      
  
    block_: AllSitesAsGuideQuery.Variables.Builder.() -> Unit
    
    ): kotlinx.coroutines.flow.Flow<AllSitesAsGuideQuery.Data> =
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
