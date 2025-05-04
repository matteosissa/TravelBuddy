package dadm.ndescot.travelbuddy.data.guide.domain

import connectors.default.AllSitesAsGuideQuery
import dadm.ndescot.travelbuddy.domain.model.Site

fun AllSitesAsGuideQuery.Data.UsersItem.GuideSitesItem.toDomain(): Site {
    return Site(
        siteName = siteName,
        countryName = countryName
    )
}