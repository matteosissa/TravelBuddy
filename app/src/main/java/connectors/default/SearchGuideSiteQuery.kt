
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


public interface SearchGuideSiteQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      DefaultConnector,
      SearchGuideSiteQuery.Data,
      SearchGuideSiteQuery.Variables
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
  
    val siteGuide:
    SiteGuide?
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class SiteGuide(
  
    val siteName:
    String,
    val countryName:
    String
  ) {
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "searchGuideSite"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun SearchGuideSiteQuery.ref(
  
    
  
    block_: SearchGuideSiteQuery.Variables.Builder.() -> Unit
  
): com.google.firebase.dataconnect.QueryRef<
    SearchGuideSiteQuery.Data,
    SearchGuideSiteQuery.Variables
  > =
  ref(
    
      SearchGuideSiteQuery.Variables.build(
        
  
    block_
      )
    
  )

public suspend fun SearchGuideSiteQuery.execute(
  
    
  
    block_: SearchGuideSiteQuery.Variables.Builder.() -> Unit
  
  ): com.google.firebase.dataconnect.QueryResult<
    SearchGuideSiteQuery.Data,
    SearchGuideSiteQuery.Variables
  > =
  ref(
    
      
  
    block_
    
  ).execute()


  public fun SearchGuideSiteQuery.flow(
    
      
  
    block_: SearchGuideSiteQuery.Variables.Builder.() -> Unit
    
    ): kotlinx.coroutines.flow.Flow<SearchGuideSiteQuery.Data> =
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
