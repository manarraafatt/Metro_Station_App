package di

import android.content.Context
import com.example.metro_station_app.data.repo.MetroRepositoryImpl
import com.example.metro_station_app.domain.useCase.GetAllStationsUseCase
import com.example.metro_station_app.presentation.viewModel.MetroViewModel
import data.dataSource.MetroJsonDataSource
import domain.useCase.BFSUseCase
import domain.useCase.CalcFareUseCase
import domain.useCase.CalcTimeUseCase
import domain.useCase.FindRoutUseCase
import domain.useCase.GetDirectionUseCase
import domain.useCase.GetFirstStationUseCase
import domain.useCase.GetLastStationUseCase
import presentation.ConsoleController

object AppModule {

    private fun provideRepository(context: Context, isArabic: Boolean): domain.MetroRepository {
        val dataSource = MetroJsonDataSource(context, isArabic)
        return MetroRepositoryImpl(dataSource)
    }

    fun provideMetroViewModel(context: Context, isArabic: Boolean = false): MetroViewModel {
        val repository = provideRepository(context, isArabic)

        val fare = CalcFareUseCase()
        val time = CalcTimeUseCase(repository)
        val bfsUseCase = BFSUseCase()

        val findRoute = FindRoutUseCase(repository, fare, time, bfsUseCase)
        val getAllStations = GetAllStationsUseCase(repository)

        return MetroViewModel(
            findRouteUseCase = findRoute,
            getAllStationsUseCase = getAllStations
        )
    }

    fun provideController(context: Context): ConsoleController {
        val dataSource = MetroJsonDataSource(context)
        val repository = MetroRepositoryImpl(dataSource)

        val fare = CalcFareUseCase()
        val time = CalcTimeUseCase(repository)
        val bfsUseCase = BFSUseCase()

        val findRoute = FindRoutUseCase(repository, fare, time, bfsUseCase)
        val getFirstStationUseCase = GetFirstStationUseCase(repository)
        val getLastStationUseCase = GetLastStationUseCase(repository)
        val direction = GetDirectionUseCase(getFirstStationUseCase, getLastStationUseCase)

        return ConsoleController(findRoute, direction)
    }
}