package com.example.myapplication.features.pdfUtils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import java.io.File
import java.io.FileOutputStream


fun createAndOpenPdf(context: Context, data: String, fileName: String) {
    try {
        val qrCodeBitmap = generateQrCode(data)
        if (qrCodeBitmap == null) {
            throw Exception("Ошибка генерации QR-кода")
        }

        val qrCodeFile = saveBitmapAsFile(context, qrCodeBitmap, "temp_qr_code.png")

        val filePath = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            "$fileName.pdf"
        )

        val writer = PdfWriter(filePath)
        val pdfDoc = PdfDocument(writer)
        val document = Document(pdfDoc)

        val imageData = ImageDataFactory.create(qrCodeFile.absolutePath)
        val qrImage = Image(imageData)
        document.add(qrImage)

        document.close()

        qrCodeFile.delete()

        openPdf(context, filePath)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun generateQrCode(data: String): Bitmap? {
    return try {
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix: BitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 500, 500)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }
        bmp
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}

private fun saveBitmapAsFile(context: Context, bitmap: Bitmap, fileName: String): File {
    val file = File(context.cacheDir, fileName)
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file
}

private fun openPdf(context: Context, file: File) {
    val uri = Uri.fromFile(file)
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    context.startActivity(Intent.createChooser(intent, "Open PDF"))
}