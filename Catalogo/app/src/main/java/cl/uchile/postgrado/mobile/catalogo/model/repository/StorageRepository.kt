package cl.uchile.postgrado.mobile.catalogo.model.repository

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.storage
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class StorageRepository(
    private val storage: FirebaseStorage = Firebase.storage
) {
    suspend fun uploadFile(
        fileUri: Uri,
        remotePath: String,
        contentType: String?,
        progressCallback: (Double) -> Unit = {}
    ): String = suspendCancellableCoroutine { cont ->
        try {
            val ref = storage.reference.child(remotePath)
            val metadata = contentType?.let { StorageMetadata
                .Builder()
                .setContentType(it)
                .build()
            }

            val uploadTask: UploadTask = if (metadata != null) {
                ref.putFile(fileUri, metadata)
            } else {
                ref.putFile(fileUri)
            }

            uploadTask.addOnProgressListener { taskSnapshot ->
                val total = taskSnapshot.totalByteCount.takeIf { it > 0 } ?: 1L
                val progress = (taskSnapshot.bytesTransferred.toDouble() / total.toDouble()) * 100.0
                progressCallback(progress)
            }
            .addOnSuccessListener { _ ->
                ref.downloadUrl
                    .addOnSuccessListener { uri ->
                        if (!cont.isCompleted) cont.resume(uri.toString())
                    }
                    .addOnFailureListener { ex ->
                        if (!cont.isCompleted) cont.resumeWithException(ex)
                    }
            }
            .addOnFailureListener { ex ->
                if (!cont.isCompleted) cont.resumeWithException(ex)
            }

            cont.invokeOnCancellation {
                uploadTask.cancel()
            }
        } catch (e: Exception) {
            if (!cont.isCompleted)
                cont.resumeWithException(e)
        }
    }

    suspend fun getDownloadUrl(remotePath: String): String = suspendCancellableCoroutine { cont ->
        val ref = storage.reference.child(remotePath)
        ref.downloadUrl
            .addOnSuccessListener { uri -> cont.resume(uri.toString()) }
            .addOnFailureListener { exception -> cont.resumeWithException(exception) }
    }

    suspend fun deleteFile(remotePath: String) = suspendCancellableCoroutine<Unit> { cont ->
        val ref = storage.reference.child(remotePath)
        ref.delete()
            .addOnSuccessListener { cont.resume(Unit) }
            .addOnFailureListener { exception -> cont.resumeWithException(exception) }
    }
}