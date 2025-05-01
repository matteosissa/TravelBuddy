
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



public interface AddNewGuideSiteMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      DefaultConnector,
      AddNewGuideSiteMutation.Data,
      AddNewGuideSiteMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val siteName:
    com.google.firebase.dataconnect.OptionalVariable<String?>,
    val countryName:
    com.google.firebase.dataconnect.OptionalVariable<String?>,
    val userId:
    com.google.firebase.dataconnect.OptionalVariable<Int?>
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      @BuilderDsl
      public interface Builder {
        public var siteName: String?
        public var countryName: String?
        public var userId: Int?
        
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
            var userId: com.google.firebase.dataconnect.OptionalVariable<Int?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
            override var siteName: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { siteName = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var countryName: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { countryName = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var userId: Int?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { userId = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              siteName=siteName,countryName=countryName,userId=userId,
            )
          }
        }
      }
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val siteGuide_upsert:
    SiteGuideKey,
    val siteGuideList_upsert:
    SiteGuideListKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddNewGuideSite"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddNewGuideSiteMutation.ref(
  
    
  
    block_: AddNewGuideSiteMutation.Variables.Builder.() -> Unit
  
): com.google.firebase.dataconnect.MutationRef<
    AddNewGuideSiteMutation.Data,
    AddNewGuideSiteMutation.Variables
  > =
  ref(
    
      AddNewGuideSiteMutation.Variables.build(
        
  
    block_
      )
    
  )

public suspend fun AddNewGuideSiteMutation.execute(
  
    
  
    block_: AddNewGuideSiteMutation.Variables.Builder.() -> Unit
  
  ): com.google.firebase.dataconnect.MutationResult<
    AddNewGuideSiteMutation.Data,
    AddNewGuideSiteMutation.Variables
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
