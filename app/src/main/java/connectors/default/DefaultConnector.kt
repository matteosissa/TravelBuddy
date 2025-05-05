
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

import com.google.firebase.dataconnect.getInstance as _fdcGetInstance

public interface DefaultConnector : com.google.firebase.dataconnect.generated.GeneratedConnector<DefaultConnector> {
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect

  
    public val addNewGuideReply: AddNewGuideReplyMutation
  
    public val addNewGuideSite: AddNewGuideSiteMutation
  
    public val addNewTrip: AddNewTripMutation
  
    public val addNewUser: AddNewUserMutation
  
    public val allAnswersToTrip: AllAnswersToTripQuery
  
    public val allSitesAsGuide: AllSitesAsGuideQuery
  
    public val allTripsByLocation: AllTripsByLocationQuery
  
    public val allTripsByUser: AllTripsByUserQuery
  
    public val deleteSite: DeleteSiteMutation
  
    public val deleteTrip: DeleteTripMutation
  
    public val getUserId: GetUserIdQuery
  

  public companion object {
    @Suppress("MemberVisibilityCanBePrivate")
    public val config: com.google.firebase.dataconnect.ConnectorConfig = com.google.firebase.dataconnect.ConnectorConfig(
      connector = "default",
      location = "us-central1",
      serviceId = "travelbuddy-73574-2-service",
    )

    public fun getInstance(
      dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
    ):DefaultConnector = synchronized(instances) {
      instances.getOrPut(dataConnect) {
        DefaultConnectorImpl(dataConnect)
      }
    }

    private val instances = java.util.WeakHashMap<com.google.firebase.dataconnect.FirebaseDataConnect, DefaultConnectorImpl>()
  }
}

public val DefaultConnector.Companion.instance:DefaultConnector
  get() = getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(config))

public fun DefaultConnector.Companion.getInstance(
  settings: com.google.firebase.dataconnect.DataConnectSettings = com.google.firebase.dataconnect.DataConnectSettings()
):DefaultConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(config, settings))

public fun DefaultConnector.Companion.getInstance(
  app: com.google.firebase.FirebaseApp,
  settings: com.google.firebase.dataconnect.DataConnectSettings = com.google.firebase.dataconnect.DataConnectSettings()
):DefaultConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(app, config, settings))

private class DefaultConnectorImpl(
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
) : DefaultConnector {
  
    override val addNewGuideReply by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddNewGuideReplyMutationImpl(this)
    }
  
    override val addNewGuideSite by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddNewGuideSiteMutationImpl(this)
    }
  
    override val addNewTrip by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddNewTripMutationImpl(this)
    }
  
    override val addNewUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddNewUserMutationImpl(this)
    }
  
    override val allAnswersToTrip by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AllAnswersToTripQueryImpl(this)
    }
  
    override val allSitesAsGuide by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AllSitesAsGuideQueryImpl(this)
    }
  
    override val allTripsByLocation by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AllTripsByLocationQueryImpl(this)
    }
  
    override val allTripsByUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AllTripsByUserQueryImpl(this)
    }
  
    override val deleteSite by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteSiteMutationImpl(this)
    }
  
    override val deleteTrip by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteTripMutationImpl(this)
    }
  
    override val getUserId by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetUserIdQueryImpl(this)
    }
  

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun operations(): List<com.google.firebase.dataconnect.generated.GeneratedOperation<DefaultConnector, *, *>> =
    queries() + mutations()

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun mutations(): List<com.google.firebase.dataconnect.generated.GeneratedMutation<DefaultConnector, *, *>> =
    listOf(
      addNewGuideReply,
        addNewGuideSite,
        addNewTrip,
        addNewUser,
        deleteSite,
        deleteTrip,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun queries(): List<com.google.firebase.dataconnect.generated.GeneratedQuery<DefaultConnector, *, *>> =
    listOf(
      allAnswersToTrip,
        allSitesAsGuide,
        allTripsByLocation,
        allTripsByUser,
        getUserId,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect) =
    DefaultConnectorImpl(dataConnect)

  override fun equals(other: Any?): Boolean =
    other is DefaultConnectorImpl &&
    other.dataConnect == dataConnect

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "DefaultConnectorImpl",
      dataConnect,
    )

  override fun toString(): String =
    "DefaultConnectorImpl(dataConnect=$dataConnect)"
}



private open class DefaultConnectorGeneratedQueryImpl<Data, Variables>(
  override val connector: DefaultConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedQuery<DefaultConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: DefaultConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    DefaultConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    DefaultConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    DefaultConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is DefaultConnectorGeneratedQueryImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "DefaultConnectorGeneratedQueryImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "DefaultConnectorGeneratedQueryImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}

private open class DefaultConnectorGeneratedMutationImpl<Data, Variables>(
  override val connector: DefaultConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedMutation<DefaultConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: DefaultConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    DefaultConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    DefaultConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    DefaultConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is DefaultConnectorGeneratedMutationImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "DefaultConnectorGeneratedMutationImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "DefaultConnectorGeneratedMutationImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}



private class AddNewGuideReplyMutationImpl(
  connector: DefaultConnector
):
  AddNewGuideReplyMutation,
  DefaultConnectorGeneratedMutationImpl<
      AddNewGuideReplyMutation.Data,
      AddNewGuideReplyMutation.Variables
  >(
    connector,
    AddNewGuideReplyMutation.Companion.operationName,
    AddNewGuideReplyMutation.Companion.dataDeserializer,
    AddNewGuideReplyMutation.Companion.variablesSerializer,
  )


private class AddNewGuideSiteMutationImpl(
  connector: DefaultConnector
):
  AddNewGuideSiteMutation,
  DefaultConnectorGeneratedMutationImpl<
      AddNewGuideSiteMutation.Data,
      AddNewGuideSiteMutation.Variables
  >(
    connector,
    AddNewGuideSiteMutation.Companion.operationName,
    AddNewGuideSiteMutation.Companion.dataDeserializer,
    AddNewGuideSiteMutation.Companion.variablesSerializer,
  )


private class AddNewTripMutationImpl(
  connector: DefaultConnector
):
  AddNewTripMutation,
  DefaultConnectorGeneratedMutationImpl<
      AddNewTripMutation.Data,
      AddNewTripMutation.Variables
  >(
    connector,
    AddNewTripMutation.Companion.operationName,
    AddNewTripMutation.Companion.dataDeserializer,
    AddNewTripMutation.Companion.variablesSerializer,
  )


private class AddNewUserMutationImpl(
  connector: DefaultConnector
):
  AddNewUserMutation,
  DefaultConnectorGeneratedMutationImpl<
      AddNewUserMutation.Data,
      AddNewUserMutation.Variables
  >(
    connector,
    AddNewUserMutation.Companion.operationName,
    AddNewUserMutation.Companion.dataDeserializer,
    AddNewUserMutation.Companion.variablesSerializer,
  )


private class AllAnswersToTripQueryImpl(
  connector: DefaultConnector
):
  AllAnswersToTripQuery,
  DefaultConnectorGeneratedQueryImpl<
      AllAnswersToTripQuery.Data,
      AllAnswersToTripQuery.Variables
  >(
    connector,
    AllAnswersToTripQuery.Companion.operationName,
    AllAnswersToTripQuery.Companion.dataDeserializer,
    AllAnswersToTripQuery.Companion.variablesSerializer,
  )


private class AllSitesAsGuideQueryImpl(
  connector: DefaultConnector
):
  AllSitesAsGuideQuery,
  DefaultConnectorGeneratedQueryImpl<
      AllSitesAsGuideQuery.Data,
      AllSitesAsGuideQuery.Variables
  >(
    connector,
    AllSitesAsGuideQuery.Companion.operationName,
    AllSitesAsGuideQuery.Companion.dataDeserializer,
    AllSitesAsGuideQuery.Companion.variablesSerializer,
  )


private class AllTripsByLocationQueryImpl(
  connector: DefaultConnector
):
  AllTripsByLocationQuery,
  DefaultConnectorGeneratedQueryImpl<
      AllTripsByLocationQuery.Data,
      AllTripsByLocationQuery.Variables
  >(
    connector,
    AllTripsByLocationQuery.Companion.operationName,
    AllTripsByLocationQuery.Companion.dataDeserializer,
    AllTripsByLocationQuery.Companion.variablesSerializer,
  )


private class AllTripsByUserQueryImpl(
  connector: DefaultConnector
):
  AllTripsByUserQuery,
  DefaultConnectorGeneratedQueryImpl<
      AllTripsByUserQuery.Data,
      AllTripsByUserQuery.Variables
  >(
    connector,
    AllTripsByUserQuery.Companion.operationName,
    AllTripsByUserQuery.Companion.dataDeserializer,
    AllTripsByUserQuery.Companion.variablesSerializer,
  )


private class DeleteSiteMutationImpl(
  connector: DefaultConnector
):
  DeleteSiteMutation,
  DefaultConnectorGeneratedMutationImpl<
      DeleteSiteMutation.Data,
      DeleteSiteMutation.Variables
  >(
    connector,
    DeleteSiteMutation.Companion.operationName,
    DeleteSiteMutation.Companion.dataDeserializer,
    DeleteSiteMutation.Companion.variablesSerializer,
  )


private class DeleteTripMutationImpl(
  connector: DefaultConnector
):
  DeleteTripMutation,
  DefaultConnectorGeneratedMutationImpl<
      DeleteTripMutation.Data,
      DeleteTripMutation.Variables
  >(
    connector,
    DeleteTripMutation.Companion.operationName,
    DeleteTripMutation.Companion.dataDeserializer,
    DeleteTripMutation.Companion.variablesSerializer,
  )


private class GetUserIdQueryImpl(
  connector: DefaultConnector
):
  GetUserIdQuery,
  DefaultConnectorGeneratedQueryImpl<
      GetUserIdQuery.Data,
      GetUserIdQuery.Variables
  >(
    connector,
    GetUserIdQuery.Companion.operationName,
    GetUserIdQuery.Companion.dataDeserializer,
    GetUserIdQuery.Companion.variablesSerializer,
  )



// The lines below are used by the code generator to ensure that this file is deleted if it is no
// longer needed. Any files in this directory that contain the lines below will be deleted by the
// code generator if the file is no longer needed. If, for some reason, you do _not_ want the code
// generator to delete this file, then remove the line below (and this comment too, if you want).

// FIREBASE_DATA_CONNECT_GENERATED_FILE MARKER 42da5e14-69b3-401b-a9f1-e407bee89a78
// FIREBASE_DATA_CONNECT_GENERATED_FILE CONNECTOR default
