

import javax.inject.Inject

class StringUtils @Inject
internal constructor() {

    /**
     * Use to replace the placeholders for strings that use the format "text {{placeholder}} text".
     *
     * @param string        string to apply substitutions on
     * @param substitutions key value pairs (placeholder, substitutionString)
     * @return string with substitutions applied
     */
    fun applySubstitutionsToString(string: String, vararg substitutions: Pair<String, String>): String {
        var primaryString = string

        for (substitution in substitutions) {
            primaryString = primaryString.replace("{{" + substitution.first + "}}", substitution.second)
        }
        return primaryString
    }
}
