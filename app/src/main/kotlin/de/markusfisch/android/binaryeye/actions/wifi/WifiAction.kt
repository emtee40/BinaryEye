package de.markusfisch.android.binaryeye.actions.wifi

import android.content.Context
import de.markusfisch.android.binaryeye.R
import de.markusfisch.android.binaryeye.actions.IAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WifiAction : IAction {
	override val iconResId = R.drawable.ic_action_wifi
	override val titleResId = R.string.connect_to_wifi

	override fun canExecuteOn(data: ByteArray): Boolean =
		WifiConnector.parse(String(data)) != null

	override suspend fun execute(
		context: Context,
		data: ByteArray
	) = withContext(Dispatchers.IO) {
		val wifiConfig = WifiConnector.parse(
			String(data)
		) ?: return@withContext
		WifiConnector.connect(context, wifiConfig)
	}
}
