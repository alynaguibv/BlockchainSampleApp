package de.n26.n26androidsamples.base.data

/**
 * Exception thrown when an essential parameter is missing in the backend/network response.
 */
class EssentialParamMissingException(missingParam: String, rawObject: Any) : RuntimeException("The params: $missingParam are missing in received object: $rawObject")
